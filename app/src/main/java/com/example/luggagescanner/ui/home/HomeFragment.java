package com.example.luggagescanner.ui.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.luggagescanner.R;
import com.example.luggagescanner.data.Scan;
import com.example.luggagescanner.databinding.FragmentHomeBinding;

import java.util.Arrays;

public class HomeFragment extends Fragment implements ScanAdapter.ScanAdapterListener {
    private FragmentHomeBinding binding;
    private ScanAdapter adapter;
    private ScanViewModel scanViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        adapter = new ScanAdapter(getActivity(), this);
        scanViewModel = new ViewModelProvider(this).get(ScanViewModel.class);

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
        Toast.makeText(cardView.getContext(), "Clicked scan with height: " + scan.getHeight(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMenuClicked(final View cardView, Scan scan) {
        PopupMenu popup = new PopupMenu(cardView.getContext(), cardView);
        popup.getMenuInflater().inflate(R.menu.menu_scan_item, popup.getMenu());
        popup.setGravity(Gravity.END);

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(cardView.getContext(), "You clicked: " + item.getTitle(), Toast.LENGTH_LONG).show();
                return true;
            }
        });

        popup.show();
    }
}
