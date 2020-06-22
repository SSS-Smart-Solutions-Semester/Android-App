package com.example.luggagescanner.data.scan;

import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "scan_table")
public class Scan {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int length;
    private int width;
    private int height;
    private boolean isAllowed;
    private String date;

    public Scan(int height, int length, int width) {
        this.height = height;
        this.length = length;
        this.width = width;

        this.isAllowed = (height <= 55 && length <= 40 && width <= 20);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.date = formatter.format(new Date());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public int getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }


    public static final DiffUtil.ItemCallback<Scan> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Scan>() {
                @Override
                public boolean areItemsTheSame(@NonNull Scan oldItem, @NonNull Scan newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Scan oldItem, @NonNull Scan newItem) {
                    return oldItem == newItem;
                }
            };
}
