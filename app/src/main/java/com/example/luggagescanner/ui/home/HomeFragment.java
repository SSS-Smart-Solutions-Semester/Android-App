package com.example.luggagescanner.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.example.luggagescanner.R;
import com.example.luggagescanner.data.Scan;
import com.example.luggagescanner.databinding.FragmentHomeBinding;

import com.google.android.material.transition.Hold;

public class HomeFragment extends Fragment implements ScanAdapter.ScanAdapterListener {
    private FragmentHomeBinding binding;
    private ScanAdapter adapter;
    private ScanViewModel scanViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setExitTransition(new Hold().setDuration((long) 300));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        adapter = new ScanAdapter(getActivity(), this);
        scanViewModel = new ViewModelProvider(requireActivity()).get(ScanViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Postpone enter transitions to allow shared element transitions to run.
        // https://github.com/googlesamples/android-architecture-components/issues/495
        postponeEnterTransition();
        OneShotPreDrawListener.add(view, this::startPostponedEnterTransition);

        binding.recyclerView.setAdapter(adapter);
        adapter.submitList(scanViewModel.getScans());
    }

    @Override
    public void onScanClicked(View cardView, Scan scan) {
        scanViewModel.setSelected(scan);

        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                .addSharedElement(cardView, cardView.getTransitionName())
                .build();

        NavDirections action = HomeFragmentDirections.actionHomeFragmentToScanFragment(scan.getId());
        Navigation.findNavController(cardView).navigate(action, extras);
    }

    @Override
    public void onMenuClicked(final View cardView, Scan scan) {
        PopupMenu popup = new PopupMenu(cardView.getContext(), cardView);
        popup.getMenuInflater().inflate(R.menu.menu_scan_item, popup.getMenu());
        popup.setGravity(Gravity.END);

        popup.setOnMenuItemClickListener(item -> {
            scanViewModel.delete(scan);
            Toast.makeText(cardView.getContext(), "You clicked: " + item.getTitle(), Toast.LENGTH_LONG).show();
            return true;
        });

        popup.show();
    }
}
