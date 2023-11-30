package com.abmtech.trading.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abmtech.trading.databinding.ItemServiceLayBinding;
import com.abmtech.trading.databinding.ItemTransactionListBinding;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemServiceLayBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemServiceLayBinding binding;
        public ViewHolder(@NonNull ItemServiceLayBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
