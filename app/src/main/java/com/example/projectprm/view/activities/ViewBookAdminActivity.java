package com.example.projectprm.view.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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

    private static final int MY_REQUEST_CODE = 10;
    private RecyclerView rcvBook;
    private Button btn_add;
    private List<Book> bookList;
    private ViewBookAdminAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book_admin);
        initUI();
        //load data
        BookRepository resBook  = new BookRepository(this.getApplication());
        bookList = resBook.getAll();
        //adapter
        adapter = new ViewBookAdminAdapter(new ViewBookAdminAdapter.IClickBook() {
            @Override
            public void updateBook(Book book) {
                clickUpdateBook(book);
            }
        });
        adapter.setData(bookList, this);
        rcvBook.setLayoutManager(new LinearLayoutManager(this));
        rcvBook.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBookAdminActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUI(){
        btn_add = findViewById(R.id.btn_addBook);
        rcvBook = findViewById(R.id.rc_viewBookAd);
    }

    private void clickUpdateBook(Book book){
        Intent intent = new Intent(ViewBookAdminActivity.this,UpdateBookActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_book", book);
        intent.putExtras(bundle);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            //load data
            BookRepository resBook  = new BookRepository(this.getApplication());
            bookList = resBook.getAll();
            //adapter
            adapter = new ViewBookAdminAdapter(new ViewBookAdminAdapter.IClickBook() {
                @Override
                public void updateBook(Book book) {
                    clickUpdateBook(book);
                }
            });
            adapter.setData(bookList, this);
            rcvBook.setLayoutManager(new LinearLayoutManager(this));
            rcvBook.setAdapter(adapter);
        }
    }
}