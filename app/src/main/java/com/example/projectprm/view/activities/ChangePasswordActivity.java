package com.example.projectprm.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.R;
import com.example.projectprm.model.repos.AccountRepository;
import com.example.projectprm.session.Session;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText editTextCurrentPassword, editTextNewPassword, editTextRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        editTextCurrentPassword = findViewById(R.id.editTextCurrentPassword);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);
        editTextRePassword = findViewById(R.id.editTextRePassword);
    }

    public void onChange(View view) {

        String currentPassword = editTextCurrentPassword.getText().toString().trim();
        String newPassword = editTextNewPassword.getText().toString().trim();
        String rePassword = editTextRePassword.getText().toString().trim();

        if (currentPassword.length() <= 0) {
            Toast.makeText(this, "Current password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (currentPassword.matches(new Session(getApplication()).getAccount().getPassword())) {
            Toast.makeText(this, "Current password incorrect!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (rePassword.length() <= 0) {
            Toast.makeText(this, "New password is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (newPassword.matches(currentPassword)) {
            Toast.makeText(this, "Can't use the current password for new", Toast.LENGTH_SHORT).show();
            return;
        }
        if (rePassword.length() <= 0) {
            Toast.makeText(this, "Confirm password is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!rePassword.matches(newPassword)) {
            Toast.makeText(this, "Confirm and new password must the same!", Toast.LENGTH_SHORT).show();
            return;
        }

        AccountRepository accountRepository =
                new AccountRepository(getApplication(), newPassword,
                        new Session(getApplication()).getAccount().getAccountId());
        if (accountRepository.changePassword() == 1) {
            Toast.makeText(this, "Change successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
