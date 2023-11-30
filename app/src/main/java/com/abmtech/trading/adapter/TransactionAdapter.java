package com.abmtech.trading.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abmtech.trading.databinding.ItemPricesLayBinding;
import com.abmtech.trading.databinding.ItemTransactionListBinding;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemTransactionListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemTransactionListBinding binding;
        public ViewHolder(@NonNull ItemTransactionListBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
