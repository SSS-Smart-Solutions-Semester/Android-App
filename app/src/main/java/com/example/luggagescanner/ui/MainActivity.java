package com.example.luggagescanner.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.DefaultCompany.HelloARU3D.UnityPlayerActivity;
import com.example.luggagescanner.ui.onboarding.Onboarding;
import com.example.luggagescanner.R;
import com.example.luggagescanner.utils.SharedPrefs;

public class MainActivity extends AppCompatActivity {
    public static final String SHOW_ONBOARDING = "show_onboarding";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean showOnboarding = SharedPrefs.readBool(this, SHOW_ONBOARDING, true);

        if (showOnboarding) {
            Intent intent = new Intent(MainActivity.this, Onboarding.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UnityPlayerActivity.class);
                startActivity(intent);
            }
        });
    }
}
