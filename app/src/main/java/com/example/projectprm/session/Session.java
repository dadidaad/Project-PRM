package com.example.projectprm.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectprm.model.entities.Account;
import com.example.projectprm.model.entities.Price;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = cntx.getSharedPreferences(cntx.getPackageName(), Context.MODE_PRIVATE);
    }

    public Map<Integer, Integer> getCart() {
        Gson gson = new Gson();
        String json = prefs.getString("cart", null);
        Type type = new TypeToken<Map<Integer, Integer>>() {
        }.getType();
        return gson.fromJson(json, type) == null ? new HashMap<>() : gson.fromJson(json, type);
    }

    public List<Price> getHistoryPrice() {
        Gson gson = new Gson();
        String json = prefs.getString("cart_history_price", null);
        Type type = new TypeToken<List<Price>>() {
        }.getType();
        return gson.fromJson(json, type) == null ? new ArrayList<>() : gson.fromJson(json, type);
    }

    public int getTotalQuantity() {
        Gson gson = new Gson();
        String json = prefs.getString("cart_total_quantity", null);
        Type type = new TypeToken<Integer>() {
        }.getType();
        return gson.fromJson(json, type) == null ? 0 : gson.fromJson(json, type);
    }

    public BigInteger getTotalPrice() {
        Gson gson = new Gson();
        String json = prefs.getString("cart_total_price", null);
        Type type = new TypeToken<BigInteger>() {
        }.getType();
        return gson.fromJson(json, type) == null ? BigInteger.ZERO : gson.fromJson(json, type);
    }

    public void saveCart(Map<Integer, Integer> cart, int totalQuantity, BigInteger totalPrice, List<Price> historyPrice) {
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        editor.putString("cart", gson.toJson(cart)).apply();
        editor.putString("cart_total_quantity", gson.toJson(totalQuantity)).apply();
        editor.putString("cart_total_price", gson.toJson(totalPrice)).apply();
        editor.putString("cart_history_price", gson.toJson(historyPrice)).apply();
    }

    public void setAccount(Account account) {
        prefs.edit().putInt("acc_id", account.getAccountId()).commit();
        prefs.edit().putString("username", account.getUsername()).commit();
        prefs.edit().putString("password", account.getPassword()).commit();
        Date dateOfBirth = account.getDateOfBirth();
        if (dateOfBirth != null) {
            prefs.edit().putString("dob", dateOfBirth.toString()).commit();
        } else {
            prefs.edit().putString("dob", "").commit();
        }
        String dispName = account.getDisplayName();
        if (dispName != null)
            prefs.edit().putString("dispName", dispName).commit();
        String address = account.getAddress();
        if (address != null)
            prefs.edit().putString("address", address).commit();
        prefs.edit().putString("type", account.getType()).commit();
    }

    public Account getAccount() {
        int acc_id = prefs.getInt("acc_id", 0);
        String username = prefs.getString("username", "");
        String password = prefs.getString("password", "");
        String dob = prefs.getString("dob", "");
        Date dateOfBirth = new Date();
        try {
            dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dispName = prefs.getString("dispName", "");
        String address = prefs.getString("address", "");
        String type = prefs.getString("type", "");

        return new Account(acc_id, username, password, dateOfBirth, dispName, address, type);
    }
}