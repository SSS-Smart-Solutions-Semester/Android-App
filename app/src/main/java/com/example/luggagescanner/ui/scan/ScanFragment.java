package com.example.luggagescanner.ui.scan;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import androidx.annotation.AttrRes;
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.luggagescanner.R;
import com.example.luggagescanner.databinding.FragmentScanBinding;
import com.example.luggagescanner.ui.home.ScanViewModel;
import com.google.android.material.transition.MaterialContainerTransform;

import java.util.Objects;

import static com.example.luggagescanner.ui.MainActivity.SCAN_CARD_TRANSITION_NAME;

public class ScanFragment extends Fragment {
    private FragmentScanBinding binding;
    private ScanViewModel scanViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareTransitions();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScanBinding.inflate(inflater, container, false);
        scanViewModel = new ViewModelProvider(requireActivity()).get(ScanViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scanViewModel.getSelected().observe(getViewLifecycleOwner(), scan ->  {
            binding.setScan(scan);
            binding.scanCardView.setTransitionName(SCAN_CARD_TRANSITION_NAME + scan.getId());
        });

        startTransitions();
    }
    private void prepareTransitions() {
        postponeEnterTransition();

        MaterialContainerTransform enterTransition = initTransition(300, R.id.nav_host_fragment);
        MaterialContainerTransform returnTransition = initTransition(250, R.id.recycler_view);

        setSharedElementEnterTransition(enterTransition);
        setSharedElementReturnTransition(returnTransition);
    }

    private void startTransitions() {
        binding.executePendingBindings();
        startPostponedEnterTransition();
    }

    private MaterialContainerTransform initTransition(int duration, @IdRes int drawingViewId) {
        int containerColor = Color.parseColor("#f7f7f7");

        MaterialContainerTransform transition = new MaterialContainerTransform();
        transition.setDuration((long) duration);
        transition.setAllContainerColors(containerColor);
        transition.setScrimColor(Color.TRANSPARENT);
        transition.setDrawingViewId(drawingViewId);

        return transition;
    }

    private int[] intToIntArray(int value) {
        String temp = Integer.toString(value);
        int[] arr = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++)
        {
            arr[i] = temp.charAt(i) - '0';
        }

        return arr;
    }
}
