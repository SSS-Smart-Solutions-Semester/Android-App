package com.example.luggagescanner.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.luggagescanner.R;
import com.example.luggagescanner.data.Scan;
import com.example.luggagescanner.databinding.FragmentScansBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScansFragment extends Fragment implements ScanAdapter.ScanAdapterListener {
    private FragmentScansBinding binding;
    private ScanAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScansBinding.inflate(inflater, container, false);

        adapter = new ScanAdapter(getActivity(), this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setAdapter(adapter);
        // TODO set list items
        adapter.submitList(Arrays.asList(
                new Scan(10, 10, 10),
                new Scan(42, 10, 10),
                new Scan(23, 10, 10),
                new Scan(26, 26, 14)
        ));

    }

    @Override
    public void onScanClicked(View cardView, Scan scan) {
        System.out.println();
        Toast.makeText(cardView.getContext(), "Clicked scan with height: " + scan.getHeight(), Toast.LENGTH_LONG).show();
    }
}
