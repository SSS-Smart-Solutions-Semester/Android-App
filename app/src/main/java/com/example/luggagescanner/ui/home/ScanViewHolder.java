package com.example.luggagescanner.ui.home;

import androidx.recyclerview.widget.RecyclerView;

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
    }
}
