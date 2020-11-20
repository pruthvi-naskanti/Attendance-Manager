package com.example.attendancemanager.other;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class SharedPref {

    private static SharedPref instance = null;

    private static final String sharedPreferencesName = "attendenceApp";

    private SharedPref() {
    }

    public static SharedPref getInstance() {
        if (instance == null) {
            instance = new SharedPref();
        }
        return instance;
    }

    public String isLoggedIn(Context context){
        SharedPreferences pref = context.getSharedPreferences(sharedPreferencesName, 0);
        return pref.getString(Constants.loginType, "");
    }

    public void setLoginType(@NonNull Context context, @NonNull String loginType) {
        SharedPreferences pref = context.getSharedPreferences(sharedPreferencesName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.loginType, loginType);
        editor.apply();
    }

    public void clearSharedPref(@NonNull Context context) {
        SharedPreferences pref = context.getSharedPreferences(sharedPreferencesName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }


}
