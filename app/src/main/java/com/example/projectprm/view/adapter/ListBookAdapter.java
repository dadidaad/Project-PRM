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

public class ListBookAdapter extends RecyclerView.Adapter<ListBookAdapter.ListBookViewHolder>{
    Context context;
    List<Book> books;
    List<Price> prices;
    private final OnClickItemRecyclerView onClickItemRecyclerView;

    public void setBookList(List<Book> filterBookList){
        this.books = filterBookList;
        notifyDataSetChanged();
    }
    public ListBookAdapter(Context context, List<Book> books, List<Price> prices, OnClickItemRecyclerView onClickItemRecyclerView) {
        this.context = context;
        this.books = books;
        this.prices = prices;
        this.onClickItemRecyclerView = onClickItemRecyclerView;
    }

    @NonNull
    @Override
    public ListBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newest_book_item, parent, false);
        return new ListBookAdapter.ListBookViewHolder(view, onClickItemRecyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBookViewHolder holder, int position) {
        holder.bookName.setText(books.get(position).getBookName());
        for(Price p : prices){
            if(p.getBookID() == books.get(position).getBookID() && p.getToDate() == null){
                holder.bookPrice.setText(String.valueOf(p.getPrice())+"đ");
                break;
            }
        }
        int resID = new PathConverter().GetResource(context,books.get(position).getImage());

        holder.bookImage.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ListBookViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImage;
        TextView bookName, bookPrice;
        //Button addToCart;
        public ListBookViewHolder(@NonNull View itemView , OnClickItemRecyclerView onClickItemRecyclerView) {
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
                            onClickItemRecyclerView.onItemClick(pos, "detail");
                        }
                    }
                }

            });
        }
    }
}
