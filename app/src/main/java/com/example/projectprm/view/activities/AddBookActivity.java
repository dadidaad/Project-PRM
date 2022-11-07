package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.repos.BookRepository;

import java.util.Date;

public class AddBookActivity extends AppCompatActivity {
    private EditText input_name,input_stock,input_cate_id,input_author_id,input_description;
    private Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        initData();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
    }


    private void initData(){
        input_name = findViewById(R.id.input_name);
        input_stock = findViewById(R.id.input_stock);
        input_cate_id = findViewById(R.id.input_cate_id);
        input_author_id = findViewById(R.id.input_author_id);
        input_description = findViewById(R.id.input_description);
        btn_add = findViewById(R.id.btn_add);
    }
    private void addBook() {
        String strBookName = input_name.getText().toString().trim();
        String strStock = input_stock.getText().toString().trim();
        String strCateId = input_cate_id.getText().toString().trim();
        String strAuthorId = input_author_id.getText().toString().trim();
        String strDes = input_description.getText().toString().trim();
        if(TextUtils.isEmpty(strBookName)||TextUtils.isEmpty(strStock)
                ||TextUtils.isEmpty(strCateId)||TextUtils.isEmpty(strAuthorId)
                ||TextUtils.isEmpty(strDes)){
            Toast.makeText(this, "Insert fail!Must be insert all field", Toast.LENGTH_SHORT).show();
            return;
        }
        //add book
        Book b = new Book();
        Date date = new Date();
        b.setAddDate(date);
        b.setBookName(strBookName);
        b.setUnitInStock(Integer.parseInt(strStock));
        b.setCatID(Integer.parseInt(strCateId));
        b.setAuthorID(Integer.parseInt(strAuthorId));
        b.setDescription(strDes);

        BookRepository res = new BookRepository(this.getApplication());
        res.insert(this.getApplication(),b);
        Toast.makeText(this, "Insert thanh cong", Toast.LENGTH_SHORT).show();
    }

}