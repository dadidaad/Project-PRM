package com.example.projectprm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;

import org.w3c.dom.Text;

import java.util.List;

public class NewestBookAdapter extends RecyclerView.Adapter<NewestBookAdapter.NewestBookViewHolder> {
    Context context;
    List<Book> books;
    List<Price> prices;

    public NewestBookAdapter(Context context, List<Book> books, List<Price> prices) {
        this.context = context;
        this.books = books;
        this.prices = prices;
    }

    @NonNull
    @Override
    public NewestBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newest_book_item, parent, false);
        return new NewestBookAdapter.NewestBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewestBookViewHolder holder, int position) {
        holder.bookName.setText(books.get(position).getBookName());
        for(Price p : prices){
            if(p.getBookID() == books.get(position).getBookID() && p.getToDate() == null){
                holder.bookPrice.setText(String.valueOf(p.getPrice()));
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class NewestBookViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImage;
        TextView bookName, bookPrice;
        //Button addToCart;
        public NewestBookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookName = itemView.findViewById(R.id.bookName);
            bookPrice = itemView.findViewById(R.id.bookPrice);
        }
    }
}
