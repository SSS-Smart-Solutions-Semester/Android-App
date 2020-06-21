package com.DefaultCompany.HelloARU3D;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ARCore extends UnityPlayerActivity {
    public static final String HEIGHT = "height";
    public static final String LENGTH = "length";
    public static final String WIDTH = "width";

    private int h, l, w;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onFinishScan(float h, float l, float w) {
        this.h = (int) h;
        this.l = (int) l;
        this.w = (int) w;

        this.goBack();
    }

    public void goBack() {
        Runnable action = () -> {
            Intent data = new Intent();
            data.putExtra(HEIGHT, h);
            data.putExtra(LENGTH, l);
            data.putExtra(WIDTH, w);
            setResult(Activity.RESULT_OK, data);

            finish();
        };

        runOnUiThread(action);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
