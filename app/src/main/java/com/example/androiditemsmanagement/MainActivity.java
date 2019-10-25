package com.example.androiditemsmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemClicked {
    RecyclerView recyclerviewMemory;
    AppDatabase db;
    ItemAdapter itemAdapter;
    public static List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        recyclerviewMemory = findViewById(R.id.recyclerview_id);
        recyclerviewMemory.setLayoutManager(new LinearLayoutManager((this)));

        final Button btn_Add = (Button) findViewById(R.id.btn_ADD);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertConfirm("Cofirm", "Would you like to add new item? ");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getandDisplayItem();
    }

    public void getandDisplayItem() {
        new AsyncTask<Void, Void, List<Item>>() {
            @Override
            protected List<Item> doInBackground(Void... voids) {
                items = db.itemDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        itemAdapter = new ItemAdapter(this, items);
                        itemAdapter.setOnClick(MainActivity.this);
                        recyclerviewMemory.setAdapter(itemAdapter);
                        Toast.makeText(MainActivity.this, "size" + items.size(), Toast.LENGTH_SHORT).show();
                    }
                });
                return null;
            }
        }.execute();
    }

    private void showAlertConfirm(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Add new item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openAddNewItem();
                    }
                })
                .show();
    }

    private void openAddNewItem() {
        Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
        startActivity(intent);
    }
}
