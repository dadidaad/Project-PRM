package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.view.adapter.ViewBookAdminAdapter;

import java.util.List;

public class ViewBookAdminActivity extends AppCompatActivity {

    private RecyclerView rcvBook;
    private Button btn_add;
    private List<Book> bookList;
    private ViewBookAdminAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book_admin);
        initUI();
        BookRepository resBook  = new BookRepository(this.getApplication());
        bookList = resBook.getAll();
        //adapter
        adapter = new ViewBookAdminAdapter(bookList, this);
        rcvBook.setLayoutManager(new LinearLayoutManager(this));
        rcvBook.setAdapter(adapter);


    }

    private void initUI(){

        btn_add = findViewById(R.id.btn_addBook);
        rcvBook = findViewById(R.id.rc_viewBookAd);
    }
}