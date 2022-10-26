package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.view.adapter.ListBookAdapter;
import com.example.projectprm.view.adapter.NewestBookAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListBookActivity extends AppCompatActivity {

    RecyclerView listBookRec;
    List<Book> bookList;
    List<Price> priceList;
    ListBookAdapter listBookAdapter;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);


        int catId = getIntent().getIntExtra("CatID",1);

        listBookRec = findViewById(R.id.listBookRec);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });


        Book book = new Book();
        book.setBookName("COTE");
        book.setBookID(1);

        Book book2 = new Book();
        book2.setBookName("SpyxFamily");
        bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book);
        bookList.add(book2);

        setListBookRecycler(bookList, priceList);
    }

    private void filterList(String newText) {
        List<Book> filteredList = new ArrayList<>();
        for (Book b : bookList){
            String name = b.getBookName().toLowerCase();
            if(b.getBookName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(b);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No Book Found", Toast.LENGTH_SHORT).show();
        }else{
            listBookAdapter.setBookList(filteredList);
        }
    }

    private void setListBookRecycler(List<Book> bookList, List<Price> priceList){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        listBookRec.setLayoutManager(layoutManager);

        listBookAdapter = new ListBookAdapter(this, bookList, priceList);
        listBookRec.setAdapter(listBookAdapter);
    }
}