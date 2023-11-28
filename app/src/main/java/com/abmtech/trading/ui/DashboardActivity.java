package com.abmtech.trading.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abmtech.trading.R;
import com.abmtech.trading.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {
    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}