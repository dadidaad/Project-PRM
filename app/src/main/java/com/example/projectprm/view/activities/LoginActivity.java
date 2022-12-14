package com.example.projectprm.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.R;
import com.example.projectprm.session.Session;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.model.repos.AccountRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onLogin(View view) {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.length() == 0) {
            Toast.makeText(this, "Username is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() == 0) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        AccountRepository accountRepository = new AccountRepository(getApplication(), username, password);
        Account account = accountRepository.login();
        int date = accountRepository.getDate();

        if (account != null) {
            Session session = new Session(this);
            session.setAccount(account);
            session.setDate(date);
            Date user = session.getAccount().getDateOfBirth();
            String pattern = "MM-dd-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date1 = simpleDateFormat.format(user);
            System.out.println(date);
            Toast.makeText(this, "Welcome " + date1, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Username or Password not valid!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}