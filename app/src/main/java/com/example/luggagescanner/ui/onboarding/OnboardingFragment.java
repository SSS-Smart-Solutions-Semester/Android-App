package com.example.luggagescanner.ui.onboarding;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.luggagescanner.R;

public class OnboardingFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private Context mCtx;

    public static OnboardingFragment newInstance(int index) {
        OnboardingFragment fragment = new OnboardingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        mCtx = this.getContext();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_onboarding, container, false);

        final ImageView ivOnboarding = root.findViewById(R.id.iv_onboarding);
        final TextView tvHeader = root.findViewById(R.id.tv_onboarding_header);
        final TextView tvBody = root.findViewById(R.id.tv_onboarding_body);

        pageViewModel.getIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer index) {
                ivOnboarding.setImageResource(getResourceId("ic_illustrations_all_0" + index, "drawable"));
                tvHeader.setText(getResourceId("header_" + index, "string"));
                tvBody.setText(getResourceId("body_" + index, "string"));
            }
        });;

        return root;
    }

    private int getResourceId(String id, String defType) {
        return getResources().getIdentifier(id, defType, mCtx.getPackageName());
    }
}