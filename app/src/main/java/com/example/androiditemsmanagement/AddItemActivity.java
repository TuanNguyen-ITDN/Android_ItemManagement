package com.example.androiditemsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {
    AppDatabase db;
    ItemAdapter itemAdapter;
    String itemName, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        final Button btn_Add_New = (Button) findViewById(R.id.btn_AddItem);
        btn_Add_New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
                finish();
            }
        });
    }

    public void addItem() {
        final EditText edit_itemName = (EditText) findViewById(R.id.editItemName);
        itemName = edit_itemName.getText().toString();

        final TextView edit_quantity = (TextView) findViewById(R.id.editItemQuantity);
        quantity = edit_quantity.getText().toString();

        if (itemName.isEmpty() && quantity.isEmpty()) {
            Toast.makeText(this, "Item name and quantity must not null", Toast.LENGTH_SHORT).show();
            return;
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Item newItem = new Item();
                newItem.setItemName(itemName);
                newItem.setQuantity(quantity);
                db.itemDao().insert(newItem);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                Toast.makeText(AddItemActivity.this, itemName + " has been added successfully", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
