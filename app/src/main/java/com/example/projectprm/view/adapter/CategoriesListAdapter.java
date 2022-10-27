package com.example.projectprm.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.R;
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.utils.converters.PathConverter;

import java.io.File;
import java.util.List;

public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.CategoriesListViewHolder> {

    Context context;
    List<Category> categoriesList;
    private final OnClickItemRecyclerView onClickItemRecyclerView;

    public CategoriesListAdapter(Context context, List<Category> categoriesList, OnClickItemRecyclerView onClickItemRecyclerView) {
        this.context = context;
        this.categoriesList = categoriesList;
        this.onClickItemRecyclerView = onClickItemRecyclerView;
    }

    @NonNull
    @Override
    public CategoriesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories_list_item, parent, false);
        return new CategoriesListViewHolder(view, onClickItemRecyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesListViewHolder holder, int position) {

        int resID = new PathConverter().GetResource(context,categoriesList.get(position).getDescription());

        holder.catLogo.setImageResource(resID);

        holder.catText.setText(categoriesList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    //Holder View
    public static class CategoriesListViewHolder extends RecyclerView.ViewHolder{

        ImageView catLogo;
        TextView catText;

        public CategoriesListViewHolder(@NonNull View itemView, OnClickItemRecyclerView onClickItemRecyclerView) {
            super(itemView);
            catLogo = itemView.findViewById(R.id.catLogo);
            catText = itemView.findViewById(R.id.catText);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(onClickItemRecyclerView != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            onClickItemRecyclerView.onItemClick(pos, "category");
                        }
                    }
                }

            });
        }
    }
}
