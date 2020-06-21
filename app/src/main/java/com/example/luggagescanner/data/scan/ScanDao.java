package com.example.luggagescanner.data.scan;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Scan scan);

    @Delete
    void delete(Scan scan);

    @Query("SELECT * from scan_table ORDER BY id DESC")
    LiveData<List<Scan>> getScans();

    @Query("DELETE FROM scan_table")
    void deleteAll();
}
