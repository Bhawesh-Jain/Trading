package com.abmtech.trading.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abmtech.trading.R;
import com.abmtech.trading.adapter.LivePricesAdapter;
import com.abmtech.trading.adapter.ServicesAdapter;
import com.abmtech.trading.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    final int duration = 20;
    final int pixelsToMove = 30;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable SCROLLING_RUNNABLE = new Runnable() {
        @Override
        public void run() {
            binding.priceRecycler.smoothScrollBy(pixelsToMove, 0);
            mHandler.postDelayed(this, duration);
        }
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.textLogin.setOnClickListener(v -> startActivity(new Intent(getContext(), LoginActivity.class)));
        binding.textRegister.setOnClickListener(v -> startActivity(new Intent(getContext(), SignupActivity.class)));

        LivePricesAdapter adapter = new LivePricesAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        binding.priceRecycler.setLayoutManager(layoutManager);
        binding.priceRecycler.setAdapter(adapter);

        binding.priceRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
                if(lastItem == layoutManager.getItemCount()-1){
                    mHandler.removeCallbacks(SCROLLING_RUNNABLE);
                    Handler postHandler = new Handler();
                    postHandler.postDelayed(() -> {
                        recyclerView.setAdapter(adapter);
                        mHandler.postDelayed(SCROLLING_RUNNABLE, 2000);
                    }, 2000);
                }
            }
        });
        mHandler.postDelayed(SCROLLING_RUNNABLE, 200);


        binding.serviceRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.serviceRecycler.setAdapter(new ServicesAdapter());

    }
}