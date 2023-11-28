package com.abmtech.trading.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abmtech.trading.R;
import com.abmtech.trading.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}