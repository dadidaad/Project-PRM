package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.OrderDetailRepository;
import com.example.projectprm.model.repos.PriceRepository;
import com.example.projectprm.model.repos.RatingRepository;
import com.example.projectprm.model.repos.WhishListRepository;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class UpdateBookActivity extends AppCompatActivity {
    private EditText input_uname,input_ustock,input_ucate_id,input_uauthor_id,input_udescription;
    private Button btn_updateBook;
    private Button btn_deleteBook;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        initData();
        book = (Book) getIntent().getExtras().get("object_book");
        if(book!= null){
            input_uname.setText(book.getBookName());
            input_ustock.setText(String.valueOf(book.getUnitInStock()));
            input_ucate_id.setText(String.valueOf(book.getCatID()));
            input_uauthor_id.setText(String.valueOf(book.getAuthorID()));
            input_udescription.setText(book.getDescription());
        }
        btn_updateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBook();
            }
        });
        btn_deleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBook();
            }
        });
    }

    private void deleteBook() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete user")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteBookYes();
                    }
                }).setNegativeButton("No", null)
                .show();
    }
    private void deleteBookYes(){

//        RatingRepository rateRes = new RatingRepository();
//        rateRes.deleteByBookId(this.getApplication(),book.getBookID());
//        OrderDetailRepository detailRes = new OrderDetailRepository();
//        detailRes.deleteByBookId(this.getApplication(),book.getBookID());
//        PriceRepository priceRes = new PriceRepository();
//        priceRes.deleteByBookId(this.getApplication(),book.getBookID());
//        WhishListRepository wishRes = new WhishListRepository();
//        wishRes.deleteByBookId(this.getApplication(), book.getBookID());
        BookRepository res = new BookRepository();
        res.delete(this.getApplication(),book);

        Toast.makeText(this, "Delete thanh cong", Toast.LENGTH_SHORT).show();
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK,intentResult);
        finish();
    }

    private void updateBook() {
        String strBookName = input_uname.getText().toString().trim();
        String strStock = input_ustock.getText().toString().trim();
        String strCateId = input_ucate_id.getText().toString().trim();
        String strAuthorId = input_uauthor_id.getText().toString().trim();
        String strDes = input_udescription.getText().toString().trim();
        if(TextUtils.isEmpty(strBookName)||TextUtils.isEmpty(strStock)
                ||TextUtils.isEmpty(strCateId)||TextUtils.isEmpty(strAuthorId)
                ||TextUtils.isEmpty(strDes)){
            Toast.makeText(this, "Insert fail!Must be insert all field", Toast.LENGTH_SHORT).show();
            return;
        }
        //update book
        Date date = new Date();
        book.setAddDate(date);
        book.setBookName(strBookName);
        book.setUnitInStock(Integer.parseInt(strStock));
        book.setCatID(Integer.parseInt(strCateId));
        book.setAuthorID(Integer.parseInt(strAuthorId));
        book.setDescription(strDes);

        BookRepository res = new BookRepository();
        res.update(this.getApplication(),book);
        Toast.makeText(this, "Update thanh cong", Toast.LENGTH_SHORT).show();

        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK,intentResult);
        finish();
    }


    private void initData(){
        input_uname = findViewById(R.id.input_uname);
        input_ustock = findViewById(R.id.input_ustock);
        input_ucate_id = findViewById(R.id.input_ucate_id);
        input_uauthor_id = findViewById(R.id.input_uauthor_id);
        input_udescription = findViewById(R.id.input_udescription);
        btn_updateBook = findViewById(R.id.btn_updateBook);
        btn_deleteBook = findViewById(R.id.btn_deleteBook);
    }
}