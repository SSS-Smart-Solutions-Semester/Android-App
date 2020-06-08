package com.example.luggagescanner.ui.onboarding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.DefaultCompany.HelloARU3D.UnityPlayerActivity;
import com.example.luggagescanner.R;
import com.example.luggagescanner.utils.SharedPrefs;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.luggagescanner.ui.MainActivity.SHOW_ONBOARDING;

public class Onboarding extends AppCompatActivity {
    public final static int TOTAL_PAGES = 6;

    private ViewPager viewPager;

    private Button navButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Setup viewpager with tab layout
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        navButton = findViewById(R.id.button_onboarding);

        // change button text based on current page
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int currentPage = viewPager.getCurrentItem();

                if (currentPage + 1 == TOTAL_PAGES)
                    navButton.setText(R.string.button_nav_done);
                else {
                    navButton.setText(R.string.button_nav_next);
                }
            }
        });
    }

    public void navigate(View v) {
        int currentPage = viewPager.getCurrentItem();
        int nextPage = currentPage + 1;

        if (currentPage + 1 == TOTAL_PAGES)
            finish();

        viewPager.setCurrentItem(nextPage, true);
    }

    public void skip(View v) {
        finish();
    }
}