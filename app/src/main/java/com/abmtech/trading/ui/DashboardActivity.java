package com.abmtech.trading.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.abmtech.trading.R;
import com.abmtech.trading.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {
    private ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                changeFragment(new HomeFragment());
                return true;
            } else if (item.getItemId() == R.id.nav_transaction) {
                changeFragment(new TransactionFragment());
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                changeFragment(new ProfileFragment());
                return true;
            } else return false;
        });

    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().add(binding.container.getId(), fragment);

        ft.commit();
    }
}