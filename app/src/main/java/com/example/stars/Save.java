package com.example.stars;

import android.content.Context;
import android.content.SharedPreferences;

public class Save{
    public static void saveInfo(Context context, String username){
        SharedPreferences sharedPreferences=context.getSharedPreferences("config",context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("login_username",username);
        editor.commit();
    }
}