package com.abmtech.trading.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.abmtech.trading.R;

public class ComplianceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compliance);

        findViewById(R.id.image_back).setOnClickListener(v -> onBackPressed());
    }
}