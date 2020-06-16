package com.example.luggagescanner.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    public static final String PREFERENCES_FILE = "luggagescanner_settings";

    public static boolean readBool(Context ctx, String preference, boolean defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(preference, defaultValue);
    }


    public static void saveBool(Context ctx, String preference, boolean value) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(preference, value);
        editor.apply();
    }

    public static void clear(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }
}
