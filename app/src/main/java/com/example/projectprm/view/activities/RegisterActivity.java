package com.example.projectprm.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.R;
import com.example.projectprm.dao.DatabaseHelper;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.model.repos.AccountRepository;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRePassword = findViewById(R.id.editTextRePassword);
    }

    public void onRegister(View view) {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirm = editTextRePassword.getText().toString().trim();

        if (username.length() == 0) {
            Toast.makeText(this, "Username is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() == 0) {
            Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirm.length() == 0) {
            Toast.makeText(this, "Confirm Password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!confirm.matches(password)) {
            Toast.makeText(this, "Password must be the same!", Toast.LENGTH_SHORT).show();
            return;
        }

        AccountRepository ar = new AccountRepository(getApplication(), username);
        if (ar.isUserExist(username)) {
            Toast.makeText(this, "Username existed! Please use another username", Toast.LENGTH_SHORT).show();
            return;
        } else {
            AccountRepository ar1 = new AccountRepository(getApplication(), username, password, "Customer");
            Toast.makeText(this, "Successfully!", Toast.LENGTH_SHORT).show();
            Login(view);
        }
    }

    public void Login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
