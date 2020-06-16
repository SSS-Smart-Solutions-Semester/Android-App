package com.example.luggagescanner.ui.home;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.luggagescanner.R;
import com.example.luggagescanner.data.Scan;
import com.example.luggagescanner.databinding.RecyclerScanItemBinding;

public class ScanViewHolder extends RecyclerView.ViewHolder {
    private final RecyclerScanItemBinding binding;

    public ScanViewHolder(RecyclerScanItemBinding binding, ScanAdapter.ScanAdapterListener listener) {
        super(binding.getRoot());
        binding.setListener(listener);

        this.binding = binding;
    }

    // TODO: bind scan props here
    public void bind(Scan scan) {
        binding.setScan(scan);

        binding.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.getMenuInflater().inflate(R.menu.menu_scan_item, popup.getMenu());
                popup.setGravity(Gravity.END);

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(v.getContext(), "You clicked: " + item.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
    }
}
