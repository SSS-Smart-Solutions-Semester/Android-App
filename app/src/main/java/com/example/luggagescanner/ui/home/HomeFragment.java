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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class HomeFragment extends Fragment implements ScanAdapter.ScanAdapterListener {
    private FragmentHomeBinding binding;
    private ScanAdapter adapter;
    private ScanViewModel scanViewModel;

    private List<Scan> scans;

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
        scans = scanViewModel.getScans();

        // TODO check is isEmpty() works on LiveData<>
        setEmptyRecyclerView(scans.isEmpty());

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
        adapter.submitList(scans);
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
            if (getResources().getString(R.string.menu_delete).equals((String) item.getTitle())) {

                new MaterialAlertDialogBuilder(cardView.getContext())
                        .setTitle(R.string.dialog_title)
                        .setMessage(R.string.dialog_message)
                        .setNegativeButton(R.string.dialog_cancel, (dialog, which) -> { })
                        .setPositiveButton(R.string.dialog_confirm, (dialog, which) -> scanViewModel.delete(scan))
                        .show();
            }

            return true;
        });

        popup.show();
    }

    private void setEmptyRecyclerView(boolean isEmpty) {
        binding.recyclerView.setVisibility(isEmpty ? View.INVISIBLE : View.VISIBLE);
        binding.recyclerEmpty.getRoot().setVisibility(isEmpty ? View.VISIBLE : View.INVISIBLE);
    }
}
