package com.example.projectprm.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectprm.R;
import com.example.projectprm.view.adapter.DialogAdapter;
import com.example.projectprm.view.adapter.OnClickItemRecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class DialogBottomFragment extends BottomSheetDialogFragment{
    private List<String> listItem;
    private OnClickItemRecyclerView onClickItemRecyclerView;
    public DialogBottomFragment(List<String> listItem, OnClickItemRecyclerView onClickItemRecyclerView) {
        this.listItem = listItem;
        this.onClickItemRecyclerView = onClickItemRecyclerView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.filter_dialog_sheet, null);
        bottomSheetDialog.setContentView(view);

        RecyclerView dialogSheet = view.findViewById(R.id.dialogSheet);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        dialogSheet.setLayoutManager(linearLayoutManager);
        DialogAdapter dialogAdapter = new DialogAdapter(listItem, onClickItemRecyclerView);

        dialogSheet.setAdapter(dialogAdapter);

        return bottomSheetDialog;
    }
}
