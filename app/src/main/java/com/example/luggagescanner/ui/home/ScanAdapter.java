package com.example.luggagescanner.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.ListAdapter;

import com.example.luggagescanner.data.Scan;
import com.example.luggagescanner.databinding.RecyclerScanItemBinding;

public class ScanAdapter extends ListAdapter<Scan, ScanViewHolder> {
    private final LayoutInflater inflater;
    private ScanAdapterListener listener;

    public interface ScanAdapterListener {
        void onScanClicked(View cardView, Scan scan);
    }

    public ScanAdapter(Context context, ScanAdapterListener listener) {
        super(Scan.DIFF_CALLBACK);
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ScanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerScanItemBinding binding = RecyclerScanItemBinding.inflate(inflater, parent, false);
        return new ScanViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
