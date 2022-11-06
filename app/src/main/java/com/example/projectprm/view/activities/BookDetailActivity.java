package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectprm.R;
import com.example.projectprm.dao.PriceDAO;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.model.repos.AuthorRepository;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.PriceRepository;
import com.example.projectprm.utils.CartHelper;
import com.example.projectprm.utils.converters.PathConverter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookDetailActivity extends AppCompatActivity {

    Button btnOpenDialogWhish;

    ImageView bookDetailImage;
    TextView txtBookDetailName;
    TextView txtBookDetailStar;
    TextView txtBookDetailPrice;
    TextView txtBookDetailUnitInStock;
    ImageView gallery1;
    ImageView gallery2;
    ImageView gallery3;
    TextView txtBookDetailID;
    TextView txtBookRelease;
    TextView txtBookAuthor;
    TextView txtDescription;

    Button btn_cmt_rate;
    CartHelper cartHelper;
    Button btn_add_to_cart;
    Button btn_add_quantity_to_cart, btn_minus_quantity_to_cart;
    TextView textViewQuantityToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        //Get Data
        int bookId = getIntent().getIntExtra("bookId", 0);

        Book book = new BookRepository(this.getApplication()).getByID(bookId);

        //Get View
        bookDetailImage = findViewById(R.id.bookDetailImage);
        gallery1 = findViewById(R.id.gallery1);
        gallery2 = findViewById(R.id.gallery2);
        gallery3 = findViewById(R.id.gallery3);
        txtBookDetailName = findViewById(R.id.txtBookDetailName);
        txtBookDetailStar = findViewById(R.id.txtBookDetailStar);
        txtBookDetailPrice = findViewById(R.id.txtBookDetailPrice);
        txtBookDetailUnitInStock = findViewById(R.id.txtBookDetailUnitInStock);
        txtBookDetailID = findViewById(R.id.txtBookDetailID);
        txtBookRelease = findViewById(R.id.txtBookRelease);
        txtBookAuthor = findViewById(R.id.txtBookAuthor);
        txtDescription = findViewById(R.id.txtDescription);

        int resID = new PathConverter().GetResource(this, book.getImage());
        bookDetailImage.setImageResource(resID);
        gallery1.setImageResource(resID);
        gallery2.setImageResource(resID);
        gallery3.setImageResource(resID);
        txtBookDetailName.setText(book.getBookName());
        txtBookDetailStar.setText(book.getAvgStars().toString());
        txtBookDetailPrice.setText(String.valueOf(new PriceRepository(this.getApplication()).getPriceBookID(bookId).getPrice()) + "đ");
        txtBookDetailUnitInStock.setText(String.valueOf(book.getUnitInStock()) + " Books Left");
        txtBookDetailID.setText(String.valueOf(book.getBookID()));
        txtBookRelease.setText(book.getAddDate().toString());
        txtBookAuthor.setText(new AuthorRepository(this.getApplication()).getAuthorById(book.getAuthorID()).getAuthorName());
        txtDescription.setText(book.getDescription());

        btn_add_quantity_to_cart = findViewById(R.id.btn_add_item_to_cart);
        btn_minus_quantity_to_cart = findViewById(R.id.btn_minus_item_to_cart);
        textViewQuantityToCart = findViewById(R.id.textViewQuantityToCart);
        //Open Comment and Rating
        btn_cmt_rate = findViewById(R.id.btn_cmt_rate);
        btn_cmt_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommentView(bookId);
            }
        });

        btnOpenDialogWhish = findViewById(R.id.btn_dialogWhish);

        btnOpenDialogWhish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogWhish(Gravity.CENTER);
            }
        });
        btn_minus_quantity_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(textViewQuantityToCart.getText().toString().trim());
                if(quantity == 1){
                    Toast.makeText(BookDetailActivity.this, "Can not least than 1", Toast.LENGTH_SHORT).show();
                    return;
                }
                --quantity;
                textViewQuantityToCart.setText(String.valueOf(quantity));
            }
        });
        btn_add_quantity_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(textViewQuantityToCart.getText().toString().trim());
                if(quantity == book.getUnitInStock()){
                    Toast.makeText(BookDetailActivity.this, "Can not more than than units in stock", Toast.LENGTH_SHORT).show();
                    return;
                }
                ++quantity;
                textViewQuantityToCart.setText(String.valueOf(quantity));
            }
        });
        cartHelper = new CartHelper(getApplication());
        btn_add_to_cart = findViewById(R.id.btn_add_to_cart);
        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(textViewQuantityToCart.getText().toString().trim());
                addToCart(bookId, quantity);
            }
        });
    }


    private void openDialogWhish(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.whishlist_dialog);

        Window window = dialog.getWindow();
        if (window == null) return;
        else {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams windowAttribute = window.getAttributes();
            windowAttribute.gravity = gravity;
        }

        window.setAttributes(window.getAttributes());
        EditText editText = findViewById(R.id.edt_desc);
        Button cancel = findViewById(R.id.btn_cancel);
        Button addWhish = findViewById(R.id.btn_addWhish);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        addWhish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookDetailActivity.this, "This book has been add to your Whishlist", Toast.LENGTH_LONG).show();
            }
        });

        dialog.show();
    }

    public void addToCart(int bookId, int quantity) {
        PriceRepository priceRepository = new PriceRepository(getApplication());
        Price lastPriceOfBook = priceRepository.getPriceBookID(bookId);
        cartHelper.add(bookId, quantity, lastPriceOfBook);
        Toast.makeText(this, "Add to cart successfully", Toast.LENGTH_SHORT).show();
    }

    public void openCommentView(int bookId) {
        Intent intent = new Intent(this, ViewCommentActivity.class);
        intent.putExtra("bookId", bookId);

        startActivity(intent);
    }
}