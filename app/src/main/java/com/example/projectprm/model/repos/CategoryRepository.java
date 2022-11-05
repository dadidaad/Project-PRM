package com.example.projectprm.model.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projectprm.dao.CategoryDao;
import com.example.projectprm.dao.room.CategoryDatabase;
import com.example.projectprm.model.entities.Category;

import java.util.List;

public class CategoryRepository {

    private CategoryDao categoryDao;

    private List<Category> allCategory;

    public CategoryRepository(Application application) {
        CategoryDatabase categoryDatabase = CategoryDatabase.getINSTANCE(application);
        categoryDao = categoryDatabase.categoryDao();
        allCategory = categoryDao.getAll();
    }

    public void insert(Category model) {
        new InsertCourseAsyncTask(categoryDao).execute(model);
    }

    // creating a method to update data in database.
    public void update(Category model) {
        new UpdateCourseAsyncTask(categoryDao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(Category model) {
        new DeleteCourseAsyncTask(categoryDao).execute(model);
    }


    // below method is to read all the courses.
    public List<Category> getAll() {
        return allCategory;
    }

    public Category get(int id) {
        return categoryDao.get(id);
    }

    private static class InsertCourseAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        private InsertCourseAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... model) {
            // below line is use to insert our modal in dao.
            categoryDao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        private UpdateCourseAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... models) {
            // below line is use to update
            // our modal in dao.
            categoryDao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        private DeleteCourseAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... models) {
            // below line is use to delete
            // our course modal in dao.
            categoryDao.delete(models[0]);
            return null;
        }
    }


}
