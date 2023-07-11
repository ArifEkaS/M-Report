package com.apkb.reportapps;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharedpref {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences prefs;

    public boolean clickedstat() {
        return prefs.getBoolean("stat", false);
    }
}
