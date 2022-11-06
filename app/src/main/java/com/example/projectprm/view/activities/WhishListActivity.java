package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.model.entities.WhishList;
import com.example.projectprm.model.repos.WhishListRepository;
import com.example.projectprm.session.Session;
import com.example.projectprm.view.adapter.ListBookAdapter;
import com.example.projectprm.view.adapter.WhishListAdapter;

import java.util.List;

public class WhishListActivity extends AppCompatActivity {

    RecyclerView listWhishBookRec;
    WhishListAdapter whishListAdapter;

    TextView txtNumberWhishBook;
    List<WhishList> whishLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whish_list);
        listWhishBookRec = findViewById(R.id.listWishBookRec);
        txtNumberWhishBook = findViewById(R.id.txtNumberOfWishBook);

        whishLists = new WhishListRepository(getApplication()).getByAcc(new Session(this).getAccount().getAccountId());

        setWhishListBook(whishLists);

    }

    private void setWhishListBook(List<WhishList> whishLists){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        listWhishBookRec.setLayoutManager(layoutManager);

        whishListAdapter = new WhishListAdapter(this, whishLists, getApplication());
        listWhishBookRec.setAdapter(whishListAdapter);
    }
}