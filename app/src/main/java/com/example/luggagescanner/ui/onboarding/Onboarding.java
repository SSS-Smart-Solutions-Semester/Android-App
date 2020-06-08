package com.example.luggagescanner.ui.onboarding;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import com.example.luggagescanner.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

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
        viewPager.setPageTransformer(true, new DepthPageTransformer());
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

    private void animatePagerTransition(final boolean forward) {

        ValueAnimator animator = ValueAnimator.ofInt(0, viewPager.getWidth());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                viewPager.endFakeDrag();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                viewPager.endFakeDrag();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            private int oldDragPosition = 0;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int dragPosition = (Integer) animation.getAnimatedValue();
                int dragOffset = dragPosition - oldDragPosition;
                oldDragPosition = dragPosition;
                viewPager.fakeDragBy(dragOffset * (forward ? -1 : 1));
            }
        });

        animator.setDuration(500);
        if (viewPager.beginFakeDrag()) {
            animator.start();
        }
    }

    public void navigate(View v) {
        int currentPage = viewPager.getCurrentItem();
        int nextPage = currentPage + 1;

        if (currentPage + 1 == TOTAL_PAGES)
            finish();

        // fake drag
        animatePagerTransition(true);
//        viewPager.setCurrentItem(nextPage, true);
    }

    public void skip(View v) {
        finish();
    }
}