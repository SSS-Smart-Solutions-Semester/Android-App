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
        Log.d(TAG, "onDestroy: 1");
        super.onDestroy();
        Log.d(TAG, "onDestroy: 2");
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: 1");
        super.onPause();
        Log.d(TAG, "onPause: 2");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed: 1");
        super.onBackPressed();
        Log.d(TAG, "onBackPressed: 2");
    }
}
