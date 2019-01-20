package com.kosanku.kelompok6.kosanku.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.kosanku.kelompok6.kosanku.HomeActivity;
import com.kosanku.kelompok6.kosanku.LoginActivity;

import java.util.HashMap;

public class Session {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Email address (make variable public to access from outside)
    public static final String KEY_IDADMIN = "idadmin";
    public static final String KEY_NAMAKOSAN = "NamaKosan";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_ALAMAT = "Alamat";
    public static final String KEY_NOHP = "Nohp";

    // Constructor
    public Session(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void createLoginSession(String idadmin, String NamaKosan, String Email, String Alamat, String Nohp){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing email in pref
        editor.putString(KEY_IDADMIN, idadmin);
        editor.putString(KEY_NAMAKOSAN, NamaKosan);
        editor.putString(KEY_EMAIL, Email);
        editor.putString(KEY_ALAMAT, Alamat);
        editor.putString(KEY_NOHP, Nohp);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If true it will redirect user to UserActivity Page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, HomeActivity.class);
            // Closing all the Activities
            i.addFlags(i.FLAG_ACTIVITY_CLEAR_TOP|i.FLAG_ACTIVITY_CLEAR_TASK|i.FLAG_ACTIVITY_NEW_TASK);

            // Staring Main Activity
            _context.startActivity(i);
        }

    }



    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        // user nama alamat email nohp id
        user.put(KEY_IDADMIN, pref.getString(KEY_IDADMIN,null));
        user.put(KEY_NAMAKOSAN, pref.getString(KEY_NAMAKOSAN, null));
        user.put(KEY_ALAMAT, pref.getString(KEY_ALAMAT,null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL,null));
        user.put(KEY_NOHP, pref.getString(KEY_NOHP,null));
        // return user
        return user;
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent intent = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP|intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(intent);
    }


    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

}

