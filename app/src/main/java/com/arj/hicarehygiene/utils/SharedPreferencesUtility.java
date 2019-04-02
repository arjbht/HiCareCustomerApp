package com.arj.hicarehygiene.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtility {

    public static final String IS_USER_LOGIN = "IS_USER_LOGIN";

    public static boolean getPrefBoolean(Context context, String key) {
        SharedPreferences prefs =
                context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static void savePrefBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs =
                context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        prefs.edit().putBoolean(key, value).apply();
    }
}
