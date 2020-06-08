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
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(v.getContext());
            }
        });


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

    private void navigate(Context ctx) {
        int currentPage = viewPager.getCurrentItem();
        int nextPage = currentPage + 1;

        if (currentPage + 1 == TOTAL_PAGES) {
//            SharedPrefs.saveBool(ctx, SHOW_ONBOARDING, false);
            Intent intent = new Intent(Onboarding.this, UnityPlayerActivity.class);
            startActivity(intent);
            finish();
        }

        viewPager.setCurrentItem(nextPage, true);
    }
}