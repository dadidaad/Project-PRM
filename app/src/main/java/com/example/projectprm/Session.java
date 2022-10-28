package com.example.projectprm;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.projectprm.model.entities.Account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setAccount(Account account) {
        prefs.edit().putString("username", account.getUsername()).commit();
        prefs.edit().putString("password", account.getPassword()).commit();
        Date dateOfBirth = account.getDateOfBirth();
        if (dateOfBirth != null)
            prefs.edit().putString("dob", dateOfBirth.toString()).commit();
        String dispName = account.getDisplayName();
        if (dispName != null)
            prefs.edit().putString("dispName", dispName).commit();
        String address = account.getAddress();
        if (address != null)
            prefs.edit().putString("address", address).commit();
        prefs.edit().putString("type", account.getType()).commit();
    }

    public Account getAccount() {
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

        return new Account(username, password, dateOfBirth, dispName, address, type);
    }
}