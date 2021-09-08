package io.bonat.customer_lib.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import io.bonat.customer_lib.data.model.Customer;
import io.bonat.customer_lib.di.qualifier.ApplicationContext;


public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_geem_pref_file";

    private final SharedPreferences mPref;

    private final String currentUserKey = "userSession_admin";
    private final String settingsKey = "settings";
    private final String fcmTokenKey = "fcm_token";
    private final String updateBadge = "update_badge";


    @NonNull
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }


    public void addWithKey(String key, String value) {
        mPref.edit().putString(key, value).apply();
    }

    public void addWithKey(String key, int value) {
        mPref.edit().putInt(key, value).apply();

    }
    public void addWithKey(String key, boolean value) {
        mPref.edit().putBoolean(key, value).apply();

    }

    @Nullable
    public String getStringWithKey(String key,String defValue) {
        return mPref.getString(key, defValue);
    }

    @Nullable
    public int getIntWithKey(String key) {
        return mPref.getInt(key, -1);
    }
    @Nullable
    public boolean getBooleanWithKey(String key) {
        return mPref.getBoolean(key, false);
    }


    public void removeKey(String key) {

        //  mPref.edit().remove(key).commit();
        mPref.edit().putString(key, "").apply();

    }

    public void removeIntKey(String key) {

        //  mPref.edit().remove(key).commit();
        mPref.edit().putInt(key, -1).apply();
    }

    public void clearSharedPreferences() {
        mPref.edit().clear().apply();
    }



  public void addUserSession(Customer user) {
        String jsonUser = gson.toJson(user);
       mPref.edit().putString(currentUserKey, jsonUser).apply();
    }

    public Customer getCurrentUser() {
        String jsonUser = mPref.getString(currentUserKey, null);
        return gson.fromJson(jsonUser, Customer.class);
    }

    public void removeUserSession() {
        //  mPref.edit().putString(currentUserKey, null).apply();
        removeKey(currentUserKey);
    }


    public void setFcmToken(Boolean aBoolean) {
        mPref.edit().putBoolean(fcmTokenKey, aBoolean).apply();
    }

    public Boolean getFcmToken() {
        return mPref.getBoolean(fcmTokenKey, false);
    }
}
