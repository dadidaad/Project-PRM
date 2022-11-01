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
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.utils.converters.PathConverter;

import java.util.List;

public class PurchasedListAdapter extends RecyclerView.Adapter<PurchasedListAdapter.PurchasedListViewHolder> {

    Context context;
    List<Book> purchasedListBook;
    List<Price> priceList;
    private final OnClickItemRecyclerView onClickItemRecyclerView;

    public PurchasedListAdapter(Context context, List<Book> purchasedListBook, OnClickItemRecyclerView onClickItemRecyclerView, List<Price> priceList) {
        this.context = context;
        this.purchasedListBook = purchasedListBook;
        this.onClickItemRecyclerView = onClickItemRecyclerView;
        this.priceList = priceList;
    }

    @NonNull
    @Override
    public PurchasedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.purchased_book_item, parent, false);
        return new PurchasedListAdapter.PurchasedListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedListViewHolder holder, int position) {
        int resID = new PathConverter().GetResource(context,purchasedListBook.get(position).getDescription());
        holder.bookImage.setImageResource(resID);
        holder.bookName.setText(purchasedListBook.get(position).getBookName());

        for(Price p : priceList){
            if(p.getBookID() == purchasedListBook.get(position).getBookID() && p.getToDate() == null){
                holder.bookPrice.setText(String.valueOf(p.getPrice()) + "đ");
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return purchasedListBook.size();
    }

    public static class PurchasedListViewHolder extends RecyclerView.ViewHolder{

        ImageView bookImage;
        TextView bookName;
        TextView bookPrice;
        public PurchasedListViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookName = itemView.findViewById(R.id.bookName);
            bookPrice = itemView.findViewById(R.id.bookPrice);

        }
    }
}
