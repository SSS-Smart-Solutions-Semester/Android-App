package com.example.luggagescanner.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.luggagescanner.data.Scan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScanViewModel extends AndroidViewModel {
    private final MutableLiveData<Scan> selected = new MutableLiveData<Scan>();
//    private LiveData<List<Scan>> scans;
    private List<Scan> scans = new ArrayList<>(Arrays.asList(
        new Scan(55, 35, 20),
        new Scan(65, 45, 20),
        new Scan(23, 10, 10),
        new Scan(26, 26, 14)));

    public ScanViewModel(@NonNull Application application) {
        super(application);
    }

    public void setSelected(Scan scan) {
        this.selected.setValue(scan);
    }

    public MutableLiveData<Scan> getSelected() {
        return this.selected;
    }

//    public LiveData<List<Scan>> getScans() {
//        return this.scans;
//    }


    public List<Scan> getScans() {
        return this.scans;
    }

    public void delete(Scan scan) {
        this.scans.remove(scan);
    }
}
