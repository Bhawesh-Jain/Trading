package com.abmtech.trading.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.abmtech.trading.databinding.ActivitySplashBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Activity activity;
    private ActivitySplashBinding binding;
    private final int SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        mAuth = FirebaseAuth.getInstance();

        init(1);
    }

    private void init(int count) {
        if (count > 10) {
            Toast.makeText(activity, "Something went wrong! Please check internet connection and try again!", Toast.LENGTH_SHORT).show();
        } else
            mAuth.signInAnonymously()
                    .addOnCompleteListener(activity, task -> {
                        if (!task.isSuccessful()) {
                            init(count + 1);
                        } else {
                            new Handler().postDelayed(() -> {
                                startActivity(new Intent(activity, LoginActivity.class));
                                finish();
                            }, SPLASH_TIMER);
                            Log.d(TAG, "onComplete() called with: task = [" + task + "]");
                        }
                    });
    }


}