package com.example.projectprm.view.contract;

import androidx.lifecycle.LiveData;

import com.example.projectprm.model.entities.Category;

import java.util.List;

public interface ViewCategoryContract {
    interface View {

    }

    interface Presenter {
        List<Category> getAllCategory();
    }
}
