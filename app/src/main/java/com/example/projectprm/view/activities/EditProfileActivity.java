package com.example.projectprm.view.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.model.repos.AccountRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Date date = new Date();
        Intent intent = getIntent();
        String fullName = intent.getStringExtra("fullName");
        String dob = intent.getStringExtra("dob");
        String address = intent.getStringExtra("address");

        DateFormat sourceFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            date = sourceFormat.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AccountRepository accountRepository =
                new AccountRepository(getApplication(), fullName, date, address);
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
}
