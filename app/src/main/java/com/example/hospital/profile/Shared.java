package com.example.hospital.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.hospital.Register.OtpPojo;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public  class Shared {

    private static SharedPreferences sharedPreferences;
    String loodedStatus;
    private static String key = "Reg";
    private static String keys = "Details";


    public static ArrayList<JsonObject> orderArrays = new ArrayList<>();
    @SuppressLint("CommitPrefEdits")
    public static void LoginShared(boolean value, Context context){
        sharedPreferences = context.getSharedPreferences(key,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("loginKey",value);
        editor.apply();
    }


    public static ArrayList<JsonObject> getOrderArrays() {
        return orderArrays;
    }

    public static void setOrderArrays(ArrayList<JsonObject> orderArrays) {
        Shared.orderArrays = orderArrays;
    }

    public static void putDetailss(OtpPojo.Result[] cusPojo, Context context){
        sharedPreferences = context.getSharedPreferences(keys,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        OtpPojo.Result cus= cusPojo[0];

        editor.putString( "name",cus.getName() != null ?cusPojo[0].getName():" ");
        editor.putString("phone",cus.getPhone_number() != null ? cusPojo[0].getPhone_number():"");
        editor.putString("email",cus.getEmail()!= null ?cusPojo[0].getEmail():"");
        editor.putString("street",cus.getAddress() != null ? cusPojo[0].getAddress():"");
        editor.putString("state",cus.getState() != null ?cusPojo[0].getState():"");
        editor.putString("city",cus.getCity() != null ? cusPojo[0].getCity():"");
        editor.putString("pincode",cus.getPostal_code() != null ? cusPojo[0].getPostal_code():"");
        editor.putString("id",cus.getCustomer_id());
        editor.apply();
    }
    static void putDetails(CusPojo.Result[] cusPojo, Context context){
        sharedPreferences = context.getSharedPreferences(keys,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        CusPojo.Result cus= cusPojo[0];

        editor.putString( "name",cus.getName() != null ? cusPojo[0].getName():" ");
        editor.putString("phone",cus.getPhone_number() != null ? cusPojo[0].getPhone_number():"");
        editor.putString("email",cus.getEmail()!= null ?cusPojo[0].getEmail():"");
        editor.putString("street",cus.getAddress() != null ? cusPojo[0].getAddress():"");
        editor.putString("state",cus.getState() != null ?cusPojo[0].getState():"");
        editor.putString("city",cus.getCity() != null ? cusPojo[0].getCity():"");
        editor.putString("pincode",cus.getPostal_code() != null ? cusPojo[0].getPostal_code():"");
        editor.putString("id",cus.getCustomer_id());
        editor.apply();
    }
    public static String id(Context context) {
        sharedPreferences = context.getSharedPreferences(keys,0);
        return sharedPreferences.getString("id", "0");
    }
    public static boolean isLogged(Context context) {
        sharedPreferences = context.getSharedPreferences(key,0);
        return sharedPreferences.getBoolean("loginKey", false);
    }
    public static String name(Context context) {
        sharedPreferences = context.getSharedPreferences(keys,0);
        return sharedPreferences.getString("name", "");
    }
    public static String address(Context context) {
        sharedPreferences = context.getSharedPreferences(keys,0);
        return sharedPreferences.getString("street", "");
    }
    public static String state(Context context) {
        sharedPreferences = context.getSharedPreferences(keys,0);
        return sharedPreferences.getString("state", "");
    }
    public static String city(Context context) {
        sharedPreferences = context.getSharedPreferences(keys,0);
        return sharedPreferences.getString("city", "");
    }
    public static String pincode(Context context) {
        sharedPreferences = context.getSharedPreferences(keys,0);
        return sharedPreferences.getString("pincode", "");
    }
    public static String phone(Context context) {
        sharedPreferences = context.getSharedPreferences(keys,0);
        return sharedPreferences.getString("phone", "");
    }
    public static String email(Context context) {
        sharedPreferences = context.getSharedPreferences(keys,0);
        return sharedPreferences.getString("email", "");
    }
}
