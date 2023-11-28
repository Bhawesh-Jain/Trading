package com.abmtech.trading.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abmtech.trading.R;
import com.abmtech.trading.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}