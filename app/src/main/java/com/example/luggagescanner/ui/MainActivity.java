package com.example.luggagescanner.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.DefaultCompany.HelloARU3D.UnityPlayerActivity;
import com.example.luggagescanner.databinding.ActivityMainBinding;
import com.example.luggagescanner.ui.onboarding.Onboarding;
import com.example.luggagescanner.R;
import com.example.luggagescanner.utils.SharedPrefs;

public class MainActivity extends AppCompatActivity {
    public static final String SCAN_CARD_TRANSITION_NAME = "scan_card_";
    public static final String SHOW_ONBOARDING = "show_onboarding";
    public static final int ONBOARDING_CODE = 123;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean showOnboarding = SharedPrefs.readBool(this, SHOW_ONBOARDING, true);

        if (showOnboarding) {
            Intent intent = new Intent(MainActivity.this, Onboarding.class);
            startActivityForResult(intent, ONBOARDING_CODE);
        }

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UnityPlayerActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Returned to main activity from onboarding screen
        if (requestCode == ONBOARDING_CODE) {
            SharedPrefs.saveBool(this, SHOW_ONBOARDING, false);
            Intent intent = new Intent(MainActivity.this, UnityPlayerActivity.class);
            startActivity(intent);
        }
    }
}
