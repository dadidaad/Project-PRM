package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.PriceRepository;
import com.example.projectprm.view.adapter.ListBookAdapter;
import com.example.projectprm.view.adapter.NewestBookAdapter;
import com.example.projectprm.view.adapter.OnClickItemRecyclerView;
import com.example.projectprm.view.fragment.DialogBottomFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ListBookActivity extends AppCompatActivity implements OnClickItemRecyclerView {

    RecyclerView listBookRec;
    List<Book> bookList;
    List<Price> priceList;
    ListBookAdapter listBookAdapter;

    List<Book> newestBookList = new ArrayList<>();

    private SearchView searchView;
    private TextView filterText;

    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);


        int catId = getIntent().getIntExtra("CatID",-1);
        tag = getIntent().getStringExtra("tag");
        listBookRec = findViewById(R.id.listBookRec);
        searchView = findViewById(R.id.searchView);
        filterText = findViewById(R.id.filterText);

        filterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOpenFilterSheet();
            }
        });


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
        priceList = new PriceRepository(this.getApplication()).getAll();
        //Init data case Category
        if(catId != -1) {
            bookList = new BookRepository(this.getApplication()).getListGetByCategory(catId);
            setListBookRecycler(bookList, priceList);
        }
        if(tag!=null) {
            if (tag.equals("newest")) {

                bookList = new BookRepository(this.getApplication()).getAll();

                for (Book b : bookList) {
                    int month = b.getAddDate().getMonth();
                    int year = b.getAddDate().getYear();
                    if (b.getAddDate().getMonth() + 1 == 10 && b.getAddDate().getYear() == 122) {
                        newestBookList.add(b);
                    }
                }
                bookList.clear();
                bookList = newestBookList;
                setListBookRecycler(newestBookList, priceList);
            }
            if (tag.equals("highestRate")) {
                bookList = new BookRepository(this.getApplication()).getHighestRatBook();
                setListBookRecycler(bookList, priceList);
            }
            if(tag.equals("contain")){
                String searchText = getIntent().getStringExtra("searchText");
                bookList = new BookRepository(this.getApplication()).getAll();
                List<Book> filterBooks = new ArrayList<>();
                for (Book b : bookList){
                    if(b.getBookName().contains(searchText)){
                        filterBooks.add(b);
                    }
                }
                bookList.clear();
                bookList = filterBooks;
                setListBookRecycler(bookList, priceList);
            }
        }
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

        listBookAdapter = new ListBookAdapter(this, bookList, priceList, this);
        listBookRec.setAdapter(listBookAdapter);
    }

    private void clickOpenFilterSheet(){
        List<String> filterData = new ArrayList<>();
        filterData.add("Higher Price");
        filterData.add("Lower Price");
        DialogBottomFragment dialogBottomFragment = new DialogBottomFragment(filterData, this);

        dialogBottomFragment.show(getSupportFragmentManager(), dialogBottomFragment.getTag());
    }

    @Override
    public void onItemClick(int position, String tag) {
        List<Book> filteredBookList = new ArrayList<>();
        List<Price> filterPriceList = new ArrayList<>();

        if(tag=="dialog") {
            switch (position) {
                case 0: {
                    filterPriceList = new PriceRepository(this.getApplication()).getAllPriceOrderDesc();
                    List<Book> newestBookList = new ArrayList<>();

                    for (Book b : bookList) {
                        int month = b.getAddDate().getMonth();
                        int year = b.getAddDate().getYear();
                        if (b.getAddDate().getMonth() + 1 == 10 && b.getAddDate().getYear() == 122) {
                            newestBookList.add(b);
                        }
                    }
                    for (Price p : filterPriceList) {
                        for (Book b : newestBookList) {
                            if (b.getBookID() == p.getBookID() && p.getToDate() == null) filteredBookList.add(b);
                        }
                    }
                    listBookAdapter.setBookList(filteredBookList);
                    break;
                }
                case 1: {
                    filterPriceList = new PriceRepository(this.getApplication()).getAllPriceOrder();
                    List<Book> newestBookList = new ArrayList<>();

                    for (Book b : bookList) {
                        int month = b.getAddDate().getMonth();
                        int year = b.getAddDate().getYear();
                        if (b.getAddDate().getMonth() + 1 == 10 && b.getAddDate().getYear() == 122) {
                            newestBookList.add(b);
                        }
                    }
                    for (Price p : filterPriceList) {
                        for (Book b : newestBookList) {
                            if (b.getBookID() == p.getBookID() && p.getToDate() == null) filteredBookList.add(b);
                        }
                    }
                    listBookAdapter.setBookList(filteredBookList);
                    break;
                }
            }
        }

        if(tag=="detail"){
            Intent intent = new Intent(this, BookDetailActivity.class);

            intent.putExtra("bookId", bookList.get(position).getBookID());

            startActivity(intent);
        }
    }
}