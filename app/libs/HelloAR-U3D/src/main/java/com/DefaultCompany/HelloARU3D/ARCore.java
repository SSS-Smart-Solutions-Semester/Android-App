package com.DefaultCompany.HelloARU3D;

import android.os.Bundle;
import android.util.Log;

public class ARCore extends UnityPlayerActivity {
    private static final String TAG = "yeet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: ");
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: ");
    }
}
