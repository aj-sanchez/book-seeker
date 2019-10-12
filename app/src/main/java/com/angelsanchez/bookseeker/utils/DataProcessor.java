package com.angelsanchez.bookseeker.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class DataProcessor {
    private static Context context;
    public final static String PREF_NAME = " com.angelsanchez.bookseeker.PREFERENCES";

    public DataProcessor(Context context){
        this.context = context;
    }

    public static void saveStringValueForKey(String key,String value){
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static String getStringValueForKey(String key){
        SharedPreferences sharedPref =  context.getSharedPreferences(PREF_NAME,0);
        return sharedPref.getString(key,"Not found");
    }

    public static void saveBooleanValueForKey(String key,Boolean value){
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME,0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public static Boolean getBooleanValueForKey(String key){
        SharedPreferences sharedPref =  context.getSharedPreferences(PREF_NAME,0);
        return sharedPref.getBoolean(key,false);
    }

}
