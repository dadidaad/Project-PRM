package com.example.projectprm.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.R;

import java.util.List;

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder> {
    private List<String> listDialogItem;
    private OnClickItemRecyclerView onClickItemRecyclerView;

    public DialogAdapter(List<String> listDialogItem, OnClickItemRecyclerView onClickItemRecyclerView) {
        this.listDialogItem = listDialogItem;
        this.onClickItemRecyclerView = onClickItemRecyclerView;
    }

    @NonNull
    @Override
    public DialogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_diaglog_bottom, parent, false);
        return new DialogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogViewHolder holder, int position) {
        String item = listDialogItem.get(position);

        holder.textView.setText(item);


    }

    @Override
    public int getItemCount() {
        return listDialogItem.size();
    }

    public class DialogViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public DialogViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.filterDialog);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(onClickItemRecyclerView != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            onClickItemRecyclerView.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
