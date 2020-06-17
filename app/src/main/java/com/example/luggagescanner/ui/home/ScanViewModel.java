package com.example.luggagescanner.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.luggagescanner.data.Scan;

import java.util.List;

public class ScanViewModel extends AndroidViewModel {
    private LiveData<List<Scan>> scans;

    public ScanViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Scan>> getScans() {
        return this.scans;
    }

    public void delete(Scan scan) {
        // TODO delete
    }
}
