package com.example.projectprm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.utils.converters.PathConverter;

import java.util.List;

public class NewestBookAdapter extends RecyclerView.Adapter<NewestBookAdapter.NewestBookViewHolder> {
    Context context;
    List<Book> books;
    List<Price> prices;
    private final OnClickItemRecyclerView onClickItemRecyclerView;
    String tag;

    public NewestBookAdapter(Context context, List<Book> books, List<Price> prices, OnClickItemRecyclerView onClickItemRecyclerView, String tag) {
        this.context = context;
        this.books = books;
        this.prices = prices;
        this.onClickItemRecyclerView = onClickItemRecyclerView;
        this.tag = tag;
    }

    @NonNull
    @Override
    public NewestBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newest_book_item, parent, false);
        return new NewestBookAdapter.NewestBookViewHolder(view, onClickItemRecyclerView, tag);
    }

    @Override
    public void onBindViewHolder(@NonNull NewestBookViewHolder holder, int position) {
        holder.bookName.setText(books.get(position).getBookName());
        for(Price p : prices){
            if(p.getBookID() == books.get(position).getBookID() && p.getToDate() == null){
                holder.bookPrice.setText(String.valueOf(p.getPrice()) + "đ");
                break;
            }
        }
        int resid = new PathConverter().GetResource(context,books.get(position).getImage());
        holder.bookImage.setImageResource(resid);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class NewestBookViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImage;
        TextView bookName, bookPrice;
        //Button addToCart;
        public NewestBookViewHolder(@NonNull View itemView, OnClickItemRecyclerView onClickItemRecyclerView, String tag) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookName = itemView.findViewById(R.id.bookName);
            bookPrice = itemView.findViewById(R.id.bookPrice);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(onClickItemRecyclerView != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            onClickItemRecyclerView.onItemClick(pos, tag);
                        }
                    }
                }

            });
        }
    }
}
