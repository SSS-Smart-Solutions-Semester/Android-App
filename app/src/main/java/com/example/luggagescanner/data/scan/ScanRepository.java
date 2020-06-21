package com.example.luggagescanner.data.scan;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.luggagescanner.data.AppDatabase;

import java.util.List;

public class ScanRepository {
    private ScanDao mScanDao;
    private LiveData<List<Scan>> mAllScans;

    public ScanRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mScanDao = db.scanDao();
        mAllScans = mScanDao.getScans();
    }

    public LiveData<List<Scan>> getAllScans() {
        return mAllScans;
    }

    public void insert(Scan scan) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mScanDao.insert(scan);
        });
    }

    public void delete(Scan scan) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mScanDao.delete(scan);
        });
    }
}
