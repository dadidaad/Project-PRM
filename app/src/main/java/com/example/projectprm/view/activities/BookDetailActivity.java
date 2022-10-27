package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectprm.R;

public class BookDetailActivity extends AppCompatActivity {

    Button btnOpenDialogWhish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        btnOpenDialogWhish = findViewById(R.id.btn_dialogWhish);

        btnOpenDialogWhish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogWhish(Gravity.CENTER);
            }
        });
    }


    private void openDialogWhish(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.whishlist_dialog);

        Window window = dialog.getWindow();
        if(window == null) return;
        else{
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams windowAttribute = window.getAttributes();
            windowAttribute.gravity = gravity;
        }

        window.setAttributes(window.getAttributes());
        EditText editText = findViewById(R.id.edt_desc);
        Button cancel = findViewById(R.id.btn_cancel);
        Button addWhish = findViewById(R.id.btn_addWhish);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        addWhish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookDetailActivity.this,"This book has been add to your Whishlist",Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }
}