package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.model.entities.WhishList;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.WhishListRepository;
import com.example.projectprm.session.Session;
import com.example.projectprm.view.adapter.ListBookAdapter;
import com.example.projectprm.view.adapter.OnClickItemRecyclerView;
import com.example.projectprm.view.adapter.WhishListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WhishListActivity extends AppCompatActivity implements OnClickItemRecyclerView {

    RecyclerView listWhishBookRec;
    WhishListAdapter whishListAdapter;

    TextView txtNumberWhishBook;
    List<WhishList> whishLists;
    SearchView searchView;

    ImageView btnWishListBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whish_list);
        listWhishBookRec = findViewById(R.id.listWishBookRec);
        searchView = findViewById(R.id.searchView);
        txtNumberWhishBook = findViewById(R.id.txtNumberOfWishBook);
        btnWishListBack = findViewById(R.id.btnWishListBack);

        btnWishListBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WhishListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        whishLists = new WhishListRepository(getApplication()).getByAcc(new Session(this).getAccount().getAccountId());

        setWhishListBook(whishLists);
        txtNumberWhishBook.setText(String.valueOf(whishLists.size()) +" Books Here");


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
    }

    private void setWhishListBook(List<WhishList> whishLists){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        listWhishBookRec.setLayoutManager(layoutManager);

        whishListAdapter = new WhishListAdapter(this, whishLists, getApplication(), this);
        listWhishBookRec.setAdapter(whishListAdapter);
    }

    private void filterList(String newText) {
        BookRepository bookRepository = new BookRepository(getApplication());
        List<WhishList> filteredList = new ArrayList<>();
        for(WhishList w : whishLists){
            Book b = bookRepository.getByID(w.getBookId());
            if(b.getBookName().toLowerCase().contains(newText.toLowerCase().trim())){
                filteredList.add(w);
            }
        }

        if(filteredList.isEmpty()){
            Toast.makeText(this, "No Book Found", Toast.LENGTH_SHORT).show();
        }else{
            whishListAdapter.setWhishList(filteredList);
        }
    }

    @Override
    public void onItemClick(int position, String tag) {
        if(tag=="wishDetail"){
            Intent intent = new Intent(this, WhishListDetailActivity.class);

            intent.putExtra("wishListBookId", whishLists.get(position).getBookId());
            intent.putExtra("wishListAccId", whishLists.get(position).getAccId());

            startActivity(intent);
        }
    }
}