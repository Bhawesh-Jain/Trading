package com.abmtech.trading.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abmtech.trading.databinding.ItemPricesLayBinding;

public class LivePricesAdapter extends RecyclerView.Adapter<LivePricesAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemPricesLayBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemPricesLayBinding binding;
        public ViewHolder(@NonNull ItemPricesLayBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
