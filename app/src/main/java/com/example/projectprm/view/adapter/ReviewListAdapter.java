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
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.Rating;
import com.example.projectprm.model.repos.AccountRepository;

import java.text.SimpleDateFormat;
import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewListViewHolder> {

    Context context;
    List<Rating> ratingList;
    List<Account> accounts;

    public ReviewListAdapter(Context context, List<Rating> ratingList, List<Account> accountList) {
        this.context = context;
        this.ratingList = ratingList;
        this.accounts = accountList;
    }

    @NonNull
    @Override
    public ReviewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_list_item, parent, false);
        return new ReviewListAdapter.ReviewListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewListViewHolder holder, int position) {
        holder.txtUserName.setText(accounts.get(position).getDisplayName());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = formatter.format(ratingList.get(position).getRateTime());
        holder.txtReviewDate.setText(strDate);
        holder.txtReviewContent.setText(ratingList.get(position).getContent());

        if(ratingList.get(position).getStars() < 1 ){
            holder.imgStar1.setVisibility(View.INVISIBLE);
        }

        if(ratingList.get(position).getStars() < 2 ){
            holder.imgStar2.setVisibility(View.INVISIBLE);
        }

        if(ratingList.get(position).getStars() < 3 ){
            holder.imgStar3.setVisibility(View.INVISIBLE);
        }

        if(ratingList.get(position).getStars() < 4 ){
            holder.imgStar4.setVisibility(View.INVISIBLE);
        }

        if(ratingList.get(position).getStars() < 5 ){
            holder.imgStar5.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return ratingList.size();
    }

    public static class ReviewListViewHolder extends RecyclerView.ViewHolder{
        TextView txtUserName;
        TextView txtReviewDate;
        TextView txtReviewContent;
        ImageView imgStar1;
        ImageView imgStar2;
        ImageView imgStar3;
        ImageView imgStar4;
        ImageView imgStar5;
        public ReviewListViewHolder(@NonNull View itemView) {

            super(itemView);

            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtReviewDate = itemView.findViewById(R.id.txtReviewDate);
            txtReviewContent = itemView.findViewById(R.id.txtReviewContent);
            imgStar1 = itemView.findViewById(R.id.imgStar1);
            imgStar2 = itemView.findViewById(R.id.imgStar2);
            imgStar3 = itemView.findViewById(R.id.imgStar3);
            imgStar4 = itemView.findViewById(R.id.imgStar4);
            imgStar5 = itemView.findViewById(R.id.imgStar5);

        }
    }
}
