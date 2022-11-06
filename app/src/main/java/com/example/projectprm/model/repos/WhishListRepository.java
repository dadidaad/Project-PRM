package com.example.projectprm.model.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projectprm.dao.CategoryDao;
import com.example.projectprm.dao.WhishListDao;
import com.example.projectprm.dao.room.CategoryDatabase;
import com.example.projectprm.dao.room.WhishListDatabase;
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.WhishList;

public class WhishListRepository {
    private WhishListDao whishListDao;

    public WhishListRepository(Application application) {
        WhishListDatabase whishListDatabase = WhishListDatabase.getINSTANCE(application);
        whishListDao = whishListDatabase.whishListDao();
    }

    public void insert(WhishList model) {
        new WhishListRepository.InsertCourseAsyncTask(whishListDao).execute(model);
    }

    // creating a method to update data in database.
    public void update(WhishList model) {
        new WhishListRepository.UpdateCourseAsyncTask(whishListDao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(WhishList model) {
        new WhishListRepository.DeleteCourseAsyncTask(whishListDao).execute(model);
    }
    private static class InsertCourseAsyncTask extends AsyncTask<WhishList, Void, Void> {
        private WhishListDao whishListDao;

        private InsertCourseAsyncTask(WhishListDao whishListDao) {
            this.whishListDao = whishListDao;
        }

        @Override
        protected Void doInBackground(WhishList... model) {
            // below line is use to insert our modal in dao.
            whishListDao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<WhishList, Void, Void> {
        private WhishListDao whishListDao;

        private UpdateCourseAsyncTask(WhishListDao whishListDao) {
            this.whishListDao = whishListDao;
        }

        @Override
        protected Void doInBackground(WhishList... models) {
            // below line is use to update
            // our modal in dao.
            whishListDao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<WhishList, Void, Void> {
        private WhishListDao whishListDao;

        private DeleteCourseAsyncTask(WhishListDao whishListDao) {
            this.whishListDao = whishListDao;
        }

        @Override
        protected Void doInBackground(WhishList... models) {
            // below line is use to delete
            // our course modal in dao.
            whishListDao.delete(models[0]);
            return null;
        }
    }
}
