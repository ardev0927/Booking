package com.jackpot.booking.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private static final String PREF_NAME = "MyBooking";

    private static final String KEY_LOGIN = "login";
    private static final String KEY_QUE = "que_info";
    private static final String KEY_FIRST = "first_load";
    private static final String KEY_REMEMBER = "remember_me";
    private static final String KEY_USERINFO = "user_info";
    public static final String KEY_METHOD = "method";
    private static final String KEY_USER = "user";

    private static PrefManager prefManager;

    public PrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setQue(int que_state) {
        editor.putInt(KEY_QUE, que_state);
        editor.commit();
    }

    public int getQue() {
        return pref.getInt(KEY_QUE, 0);
    }

    public void setFirst(boolean value) {
        editor.putBoolean(KEY_FIRST, value);
        editor.commit();
    }

    public boolean getFirst() {
        return pref.getBoolean(KEY_FIRST, false);
    }

    public void setRemember(boolean value) {
        editor.putBoolean(KEY_REMEMBER, value);
        editor.commit();
    }

    public boolean getRemember() {
        return pref.getBoolean(KEY_REMEMBER, false);
    }

    public void setLoginInfo(String value) {
        editor.putString(KEY_USERINFO, value);
        editor.commit();
    }

    public String getLoginInfo() {
        return pref.getString(KEY_USERINFO, "");
    }

    public void doLogin(boolean value) {
        editor.putBoolean(KEY_LOGIN, value);
        editor.commit();
    }

    public boolean isLogin() {
        return pref.getBoolean(KEY_LOGIN, false);
    }

    public void setUser(String value) {
        editor.putString(KEY_USER, value);
        editor.commit();
    }

    public String getUser() {
        return pref.getString(KEY_USER, "");
    }
}
