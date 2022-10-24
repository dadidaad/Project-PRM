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
import com.example.projectprm.model.entities.Category;

import java.util.List;

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CategoriesListViewHolder> {

    Context context;
    List<Category> categoriesList;

    public CategoriesListAdapter(Context context, List<Category> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public CategoriesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_list_item, parent, false);
        return new CategoriesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesListViewHolder holder, int position) {
        holder.catLogo.setImageResource(R.drawable.book_logo);
        holder.catText.setText(categoriesList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public static class CategoriesListViewHolder extends RecyclerView.ViewHolder{

        ImageView catLogo;
        TextView catText;

        public CategoriesListViewHolder(@NonNull View itemView) {
            super(itemView);
            catLogo = itemView.findViewById(R.id.catLogo);
            catText = itemView.findViewById(R.id.catText);
        }
    }
}
