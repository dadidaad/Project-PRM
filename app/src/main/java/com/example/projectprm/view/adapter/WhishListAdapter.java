package com.example.projectprm.view.adapter;

import android.app.Application;
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
import com.example.projectprm.model.entities.WhishList;
import com.example.projectprm.model.repos.BookRepository;
import com.example.projectprm.model.repos.PriceRepository;
import com.example.projectprm.utils.converters.PathConverter;

import java.util.List;

public class WhishListAdapter extends RecyclerView.Adapter<WhishListAdapter.WhishListViewHolder> {
    Context context;
    Application application;
    List<WhishList> whishLists;

    public WhishListAdapter(Context context, List<WhishList> whishLists, Application application) {
        this.context = context;
        this.whishLists = whishLists;
        this.application = application;
    }

    @NonNull
    @Override
    public WhishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.whishlist_item, parent, false);
        return new WhishListAdapter.WhishListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhishListViewHolder holder, int position) {
        Book book = new BookRepository(application).getByID(whishLists.get(position).getBookId());
        Price price = new PriceRepository(application).getPriceBookID(book.getBookID());

        int resid = new PathConverter().GetResource(context,book.getImage());
        holder.whishListBookImage.setImageResource(resid);
        holder.whishListBookName.setText(book.getBookName());
        holder.whishListBookPrice.setText(price.getPrice());
    }

    @Override
    public int getItemCount() {
        return whishLists.size();
    }

    public static class WhishListViewHolder extends RecyclerView.ViewHolder{
        ImageView whishListBookImage;
        TextView whishListBookName;
        TextView whishListBookPrice;
        public WhishListViewHolder(@NonNull View itemView) {
            super(itemView);
            whishListBookImage = itemView.findViewById(R.id.bookImage);
            whishListBookName = itemView.findViewById(R.id.bookName);
            whishListBookPrice = itemView.findViewById(R.id.bookPrice);
        }
    }
}
