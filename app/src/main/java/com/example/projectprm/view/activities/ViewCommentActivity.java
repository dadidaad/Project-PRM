package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Rating;
import com.example.projectprm.model.repos.AccountRepository;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.RatingRepository;
import com.example.projectprm.view.adapter.NewestBookAdapter;
import com.example.projectprm.view.adapter.ReviewListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewCommentActivity extends AppCompatActivity {

    TextView txtBookReviewName;
    TextView txtReviewTotal;
    RecyclerView reviewListRec;
    ReviewListAdapter reviewListAdapter;

    private List<Rating> ratingList;
    private List<Account> accountList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comment);

        int bookId = getIntent().getIntExtra("bookId", 0);

        Book book = new BookRepository(this.getApplication()).getByID(bookId);

        txtBookReviewName = findViewById(R.id.txtBookReviewName);
        txtReviewTotal = findViewById(R.id.txtReviewTotal);
        reviewListRec = findViewById(R.id.reviewListRec);

        txtBookReviewName.setText(book.getBookName());

        ratingList = new RatingRepository(this.getApplication()).getRatingsBook(book.getBookID());

        float star = 0;

        for(Rating r : ratingList){
            star += r.getStars();
        }
        star = star/ratingList.size();
        star = Math.round(star * 10) / 10;
        txtReviewTotal.setText(String.valueOf(ratingList.size()) + " Reviews (" + star + "/5)");
        accountList = new ArrayList<>();
        ratingList = new RatingRepository(this.getApplication()).getRatingsBook(bookId);
        for(Rating r : ratingList){
            Account a = new AccountRepository(this.getApplication()).getById(r.getAccountId());
            accountList.add(a);
        }

        setAdapterRatingRec(ratingList, accountList);
    }

    private void setAdapterRatingRec(List<Rating> ratingList,List<Account> accountList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reviewListRec.setLayoutManager(layoutManager);

        reviewListAdapter = new ReviewListAdapter(this, ratingList, accountList);
        reviewListRec.setAdapter(reviewListAdapter);
    }
}