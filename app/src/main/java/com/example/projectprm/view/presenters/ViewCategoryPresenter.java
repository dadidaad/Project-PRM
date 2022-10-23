package com.example.projectprm.view.presenters;

import androidx.lifecycle.LiveData;

import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.repos.CategoryRepository;
import com.example.projectprm.view.contract.ViewCategoryContract;

import java.util.List;

public class ViewCategoryPresenter implements ViewCategoryContract.Presenter {
    private ViewCategoryContract.View view;
    private CategoryRepository categoryRepository;

    public ViewCategoryPresenter(CategoryRepository categoryViewModal) {
        this.categoryRepository = categoryViewModal;
    }

    public ViewCategoryPresenter() {

    }

    public void setView(ViewCategoryContract.View view) {
        this.view = view;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.getAll();
    }
}
