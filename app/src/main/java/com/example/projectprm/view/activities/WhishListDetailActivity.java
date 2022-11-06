package com.example.projectprm.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.model.entities.WhishList;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.PriceRepository;
import com.example.projectprm.model.repos.WhishListRepository;
import com.example.projectprm.utils.converters.PathConverter;

import org.w3c.dom.Text;

public class WhishListDetailActivity extends AppCompatActivity {

    int bookId;
    int accId;

    ImageView wishListDetailCover;
    TextView wishListDetailName;
    TextView wishListDetailPrice;
    TextView wishListDetailStock;
    TextView txtDeleteFromWhishList;

    EditText edtWishListDes;

    Button btnUpdateWishList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whish_list_detail);

        wishListDetailCover = findViewById(R.id.wishListDetailCover);
        wishListDetailName = findViewById(R.id.wishListDetailName);
        wishListDetailPrice = findViewById(R.id.wishListDetailPrice);
        wishListDetailStock = findViewById(R.id.wishListDetailStock);
        edtWishListDes = findViewById(R.id.edtWishListDes);
        btnUpdateWishList = findViewById(R.id.btnUpdateWishList);
        txtDeleteFromWhishList = findViewById(R.id.txtDeleteFromWhishList);

        bookId = getIntent().getIntExtra("wishListBookId", 0);
        accId = getIntent().getIntExtra("wishListAccId", 0);

        Book b =new BookRepository(getApplication()).getByID(bookId);
        WhishList w = new WhishListRepository(getApplication()).getByAccIdBookId(accId, bookId);
        Price p = new PriceRepository(getApplication()).getPriceBookID(b.getBookID());

        int resID = new PathConverter().GetResource(this, b.getImage());
        wishListDetailCover.setImageResource(resID);
        wishListDetailName.setText(b.getBookName());
        wishListDetailPrice.setText(String.valueOf(p.getPrice()) + " đ");
        wishListDetailStock.setText(String.valueOf(b.getUnitInStock()) + " Book Mores");

        edtWishListDes.setText(w.getDesc());

        btnUpdateWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w.setDesc(edtWishListDes.getText().toString().trim());
                new WhishListRepository(getApplication()).update(w);

                Toast.makeText(WhishListDetailActivity.this, "Update WishList Description!", Toast.LENGTH_LONG).show();
            }
        });

        txtDeleteFromWhishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WhishListRepository(getApplication()).delete(w);

                Intent intent = new Intent(WhishListDetailActivity.this, WhishListActivity.class);
                startActivity(intent);
            }
        });
    }
}