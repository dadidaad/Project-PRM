package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Order;
import com.example.projectprm.model.entities.OrderDetail;
import com.example.projectprm.model.entities.Rating;
import com.example.projectprm.model.repos.AccountRepository;
import com.example.projectprm.model.repos.AuthorRepository;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.CategoryRepository;
import com.example.projectprm.model.repos.OrderDetailRepository;
import com.example.projectprm.model.repos.OrderRepository;
import com.example.projectprm.model.repos.RatingRepository;
import com.example.projectprm.session.Session;
import com.example.projectprm.utils.converters.PathConverter;

import java.util.Date;
import java.util.List;

public class CommentAndRateActivity extends AppCompatActivity {

    ImageView imgCommentBookCover;
    TextView txtCommentBookName;
    TextView txtCommentAuthor;
    TextView txtCommentCategory;
    TextView txtCommentAvgStar;

    ImageView imgVote1;
    ImageView imgVote2;
    ImageView imgVote3;
    ImageView imgVote4;
    ImageView imgVote5;

    EditText edtComment;

    Button btnComment;

    int bookId;
    int vote = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_and_rate);

        imgCommentBookCover = findViewById(R.id.imgCommentBookCover);
        txtCommentBookName = findViewById(R.id.txtCommentBookName);
        txtCommentAuthor = findViewById(R.id.txtCommentAuthor);
        txtCommentCategory = findViewById(R.id.txtCommentCategory);
        txtCommentAvgStar = findViewById(R.id.txtCommentAvgStar);

        imgVote1 = findViewById(R.id.imgVote1);
        imgVote2 = findViewById(R.id.imgVote2);
        imgVote3 = findViewById(R.id.imgVote3);
        imgVote4 = findViewById(R.id.imgVote4);
        imgVote5 = findViewById(R.id.imgVote5);

        edtComment= findViewById(R.id.edtComment);

        bookId = getIntent().getIntExtra("bookId",0);

        Book book = new BookRepository(this.getApplication()).getByID(bookId);

        int resId = new PathConverter().GetResource(this, book.getImage());

        imgCommentBookCover.setImageResource(resId);
        txtCommentBookName.setText(book.getBookName());
        txtCommentAuthor.setText(new AuthorRepository(this.getApplication()).getAuthorById(book.getAuthorID()).getAuthorName());
        txtCommentCategory.setText(new CategoryRepository(this.getApplication()).get(book.getCatID()).getCategoryName());
        txtCommentAvgStar.setText(String.valueOf(book.getAvgStars()));


        imgVote1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgVote1.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote2.setColorFilter(null);
                imgVote3.setColorFilter(null);
                imgVote4.setColorFilter(null);
                imgVote5.setColorFilter(null);
                vote = 1;
            }
        });

        imgVote2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgVote1.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote2.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote3.setColorFilter(null);
                imgVote4.setColorFilter(null);
                imgVote5.setColorFilter(null);
                vote = 2;
            }
        });

        imgVote3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgVote1.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote2.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote3.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote4.setColorFilter(null);
                imgVote5.setColorFilter(null);
                vote = 3;
            }
        });

        imgVote4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgVote1.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote2.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote3.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote4.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote5.setColorFilter(null);
                vote = 4;
            }
        });

        imgVote5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgVote1.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote2.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote3.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote4.setColorFilter(getResources().getColor(R.color.yellow));
                imgVote5.setColorFilter(getResources().getColor(R.color.yellow));
                vote = 5;
            }
        });

        btnComment = findViewById(R.id.btnComment);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkOrder = false;
                Account acc = new Session(getApplication()).getAccount();
                List<OrderDetail> orderDetailList = new OrderDetailRepository(getApplication()).getByBookId(bookId);
                for(OrderDetail o : orderDetailList){
                    int id = o.getOrder_id();
                    Order order = new OrderRepository(getApplication()).getById(id);
                    if(order != null){
                        if(order.getCustomerId() == acc.getAccountId()) checkOrder = true;
                    }
                }

                if(!checkOrder){
                    Toast.makeText(CommentAndRateActivity.this, "You have not bought this book yet", Toast.LENGTH_SHORT).show();
                }else{
                    Rating rating = new Rating();
                    rating.setRateId(4);
                    rating.setStars(vote);
                    rating.setAccountId(acc.getAccountId());
                    rating.setContent(edtComment.getText().toString());
                    rating.setBookId(bookId);
                    rating.setOrderId(1);
                    rating.setRateTime(new Date());

                    new RatingRepository(getApplication()).insert(rating);
                    Intent intent = new Intent(CommentAndRateActivity.this, ViewCommentActivity.class);
                    intent.putExtra("bookId", bookId);
                    startActivity(intent);
                    Toast.makeText(CommentAndRateActivity.this, "Your comment have Added", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}