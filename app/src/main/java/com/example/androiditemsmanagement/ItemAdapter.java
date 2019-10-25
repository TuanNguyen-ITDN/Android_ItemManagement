package com.example.androiditemsmanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    public List<Item> items;
    private OnItemClicked onClick;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.tvitemName.setText(items.get(position).getItemName());
        holder.tvquantity.setText(items.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public interface OnItemClicked {
    }

    public void setOnClick(OnItemClicked onClick) {
        this.onClick = onClick;
    }

    public ItemAdapter(Runnable mainActivity, List<Item> Items) {
        items = Items;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvitemName;
        TextView tvquantity;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvitemName = itemView.findViewById(R.id.tvItemname);
            tvquantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
}
