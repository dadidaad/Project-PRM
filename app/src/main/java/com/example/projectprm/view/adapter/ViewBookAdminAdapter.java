package com.example.projectprm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Book;

import java.util.List;

public class ViewBookAdminAdapter extends RecyclerView.Adapter<ViewBookAdminAdapter.ViewBookAdminHolder> {

    private List<Book> bookList;
    private Context adContext;
    private IClickBook iClickBook;

    public interface IClickBook{
        void updateBook(Book book);
    }

    public ViewBookAdminAdapter(IClickBook iClickBook) {
        this.iClickBook = iClickBook;
    }

    public void setData(List<Book> bookList, Context adContext) {
        notifyDataSetChanged();
        this.bookList = bookList;
        this.adContext = adContext;
    }

    @NonNull
    @Override
    public ViewBookAdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(adContext).inflate(R.layout.item_view_book_admin_row,parent,false);
        return new ViewBookAdminHolder(v);
    }

    @Override
    public void onBindViewHolder( @NonNull ViewBookAdminHolder holder, int position) {
        final Book book = bookList.get(position);
        if(bookList.get(position)==null) return;
        holder.tv_book_id.setText(String.valueOf(bookList.get(position).getBookID()));
        holder.tv_book_name.setText(bookList.get(position).getBookName());
        holder.tv_stock.setText(String.valueOf(bookList.get(position).getUnitInStock()));
        holder.tv_cate_id.setText(String.valueOf(bookList.get(position).getCatID()));

        //Recyclerview onClickListener
        holder.viewbookrowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickBook.updateBook(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewBookAdminHolder extends RecyclerView.ViewHolder{
        public TextView tv_book_id;
        public TextView tv_book_name;
        public TextView tv_stock;
        public TextView tv_cate_id;
        public ConstraintLayout viewbookrowLayout;

        public ViewBookAdminHolder(@NonNull View itemView) {
            super(itemView);
            tv_book_id = itemView.findViewById(R.id.tvBookId);
            tv_book_name = itemView.findViewById(R.id.tvBookName);
            tv_stock = itemView.findViewById(R.id.tvStock);
            tv_cate_id = itemView.findViewById(R.id.tvCateId);
            viewbookrowLayout = itemView.findViewById(R.id.viewbookrowLayout);
        }
    }
}
