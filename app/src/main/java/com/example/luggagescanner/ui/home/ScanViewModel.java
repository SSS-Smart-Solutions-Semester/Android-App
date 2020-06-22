package com.example.luggagescanner.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.luggagescanner.data.scan.Scan;
import com.example.luggagescanner.data.scan.ScanRepository;

import java.util.List;

public class ScanViewModel extends AndroidViewModel {
    private ScanRepository repository;

    private LiveData<List<Scan>> scans;
    private final MutableLiveData<Scan> selected = new MutableLiveData<Scan>();

    public ScanViewModel(@NonNull Application application) {
        super(application);
        repository = new ScanRepository(application);
        scans = repository.getAllScans();
    }

    public void setSelected(Scan scan) {
        this.selected.setValue(scan);
    }

    public MutableLiveData<Scan> getSelected() {
        return this.selected;
    }

    public LiveData<List<Scan>> getScans() {
        return this.scans;
    }

    public void insert(Scan scan) {
        repository.insert(scan);
    }

    public void delete(Scan scan) {
        repository.delete(scan);
    }
}
