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
import com.example.projectprm.utils.CartHelper;
import com.example.projectprm.utils.converters.PathConverter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {
    Context context;
    List<CartItem> cartItems;
    OnCartItemListener onCartItemListener;

    public CartItemAdapter(Context context, List<CartItem> cartItems, OnCartItemListener onCartItemListener) {
        this.context = context;
        this.cartItems = cartItems;
        this.onCartItemListener = onCartItemListener;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false);
        return new CartItemAdapter.CartItemViewHolder(view, onCartItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
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

    class CartItemViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView bookName, bookCategory, bookAuthor, bookQuantity, bookOldPrice, bookNewPrice, bookId;
        OnCartItemListener onCartItemListener;

        ImageView cartAdd, cartMinus, cartDelete;
        public CartItemViewHolder(@NonNull View itemView, OnCartItemListener onCartItemListener) {
            super(itemView);
            this.onCartItemListener = onCartItemListener;
            bookImage = itemView.findViewById(R.id.book_img);
            bookName = itemView.findViewById(R.id.book_name);
            bookCategory = itemView.findViewById(R.id.book_cat_name);
            bookAuthor = itemView.findViewById(R.id.book_author_name);
            bookQuantity = itemView.findViewById(R.id.book_quantity);
            bookOldPrice = itemView.findViewById(R.id.book_old_price);
            bookNewPrice = itemView.findViewById(R.id.book_new_price);
            bookId = itemView.findViewById(R.id.book_id);
            cartAdd = itemView.findViewById(R.id.cart_add);
            cartMinus = itemView.findViewById(R.id.cart_minus);
            cartDelete = itemView.findViewById(R.id.cart_delete);
            this.onCartItemListener = onCartItemListener;
            cartAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    onCartItemListener.onQuantityIncreased(getAdapterPosition());
                }
            });
            cartMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    onCartItemListener.onQuantityReduced(getAdapterPosition());
                }
            });
            cartDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    onCartItemListener.onItemRemoved(getAdapterPosition());
                }
            });

        }
    }
    public interface OnCartItemListener{
        void onQuantityReduced(int position);
        void onQuantityIncreased(int position);
        void onItemRemoved(int position);
    }
}
