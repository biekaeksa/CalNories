package com.spiderman.calnories.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.spiderman.calnories.data.UserModel;

/**
 * Created by Biekaeksa on 3/14/2017.
 */

public class SessionUtils {
    public static boolean login(Context context, UserModel userModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                AppConstants.PreferencesKey.KEY_USER_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String userJson = new Gson().toJson(userModel);
        editor.putString(AppConstants.PreferencesKey.USER_SESSION, userJson);
        editor.apply();
        return true;
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                AppConstants.PreferencesKey.KEY_USER_SESSION, Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString(AppConstants.PreferencesKey.USER_SESSION, null);
        if (userJson != null) {
            return true;
        } else {
            return false;
        }
    }

    public static UserModel getLoggedUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                AppConstants.PreferencesKey.KEY_USER_SESSION, Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString(AppConstants.PreferencesKey.USER_SESSION, null);
        if (userJson != null) {
            UserModel user = new Gson().fromJson(userJson, UserModel.class);
            return user;
        } else
            return null;
    }

    public static boolean logout(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                AppConstants.PreferencesKey.KEY_USER_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        return true;
    }
}
