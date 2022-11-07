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
import com.example.projectprm.model.CartItem;
import com.example.projectprm.utils.converters.PathConverter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderItemListAdapter extends RecyclerView.Adapter<OrderItemListAdapter.OrderItemViewHolder> {
    Context context;
    List<CartItem> cartItems;

    public OrderItemListAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }


    @NonNull
    @Override
    public OrderItemListAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_list_item, parent, false);
        return new OrderItemListAdapter.OrderItemViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull OrderItemListAdapter.OrderItemViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        int resID = new PathConverter().GetResource(context, cartItem.getBook().getImage());
        Picasso.with(context).load(resID).into(holder.bookImage);
        holder.bookName.setText(cartItem.getBook().getBookName());
        holder.bookCategory.setText(cartItem.getCategoryName());
        holder.bookAuthor.setText(cartItem.getAuthorName());
        holder.bookQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.bookId.setText(String.valueOf(cartItem.getBook().getBookID()));
        holder.bookOldPrice.setText(cartItem.getOldPrice() == null ? "" : String.valueOf(cartItem.getOldPrice().getPrice()));
        holder.bookNewPrice.setText(String.valueOf(cartItem.getNewPrice().getPrice()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class OrderItemViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView bookName, bookCategory, bookAuthor, bookQuantity, bookOldPrice, bookNewPrice, bookId;

        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.book_img);
            bookName = itemView.findViewById(R.id.book_name);
            bookCategory = itemView.findViewById(R.id.book_cat_name);
            bookAuthor = itemView.findViewById(R.id.book_author_name);
            bookQuantity = itemView.findViewById(R.id.book_quantity);
            bookOldPrice = itemView.findViewById(R.id.book_old_price);
            bookNewPrice = itemView.findViewById(R.id.book_new_price);
            bookId = itemView.findViewById(R.id.book_id);
        }
    }
}
