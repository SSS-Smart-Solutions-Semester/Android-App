package com.example.luggagescanner.data;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Scan {
    private static int count = 0;
    private int id;
    private int length;
    private int width;
    private int height;
    private boolean isAllowed;

    public Scan(int l, int w, int h) {
        this.length = l;
        this.width = w;
        this.height = h;

        this.isAllowed = true;
        this.id = count++;
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
        return "07-05-2020 12:30";
    }


    public static final DiffUtil.ItemCallback<Scan> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Scan>() {
                @Override
                public boolean areItemsTheSame(@NonNull Scan oldItem, @NonNull Scan newItem) {
                    // TODO return are items same
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Scan oldItem, @NonNull Scan newItem) {
                    return oldItem == newItem;
                }
            };
}
