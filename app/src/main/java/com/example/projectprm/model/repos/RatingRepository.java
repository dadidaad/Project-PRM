package com.example.projectprm.model.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projectprm.dao.AuthorDao;
import com.example.projectprm.dao.RatingDao;
import com.example.projectprm.dao.room.AuthorDatabase;
import com.example.projectprm.dao.room.RatingDatabase;
import com.example.projectprm.model.entities.Author;
import com.example.projectprm.model.entities.Rating;

import java.util.List;

public class RatingRepository {
    private RatingDao ratingDao;
    private List<Rating> ratings;
    private List<Rating> ratingsBook;
    private Rating rating;

    public RatingRepository(Application application) {
        RatingDatabase ratingDatabase = RatingDatabase.getINSTANCE(application);
        ratingDao = ratingDatabase.ratingDao();
        ratings = ratingDao.getAll();
    }
    public void insert(Rating model) {
        new RatingRepository.InsertCourseAsyncTask(ratingDao).execute(model);
    }

    // creating a method to update data in database.
    public void update(Rating model) {
        new RatingRepository.UpdateCourseAsyncTask(ratingDao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(Rating model) {
        new RatingRepository.DeleteCourseAsyncTask(ratingDao).execute(model);
    }

    public List<Rating> getAll() {
        return ratingDao.getAll();
    }
    public List<Rating> getRatingsBook(int bookId) {return ratingDao.getByBookId(bookId);}

    private static class InsertCourseAsyncTask extends AsyncTask<Rating, Void, Void> {
        private RatingDao ratingDao;

        private InsertCourseAsyncTask(RatingDao ratingDao) {
            this.ratingDao = ratingDao;
        }

        @Override
        protected Void doInBackground(Rating... model) {
            // below line is use to insert our modal in dao.
            ratingDao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<Rating, Void, Void> {
        private RatingDao ratingDao;

        private UpdateCourseAsyncTask(RatingDao ratingDao) {
            this.ratingDao = ratingDao;
        }

        @Override
        protected Void doInBackground(Rating... models) {
            // below line is use to update
            // our modal in dao.
            ratingDao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<Rating, Void, Void> {
        private RatingDao ratingDao;

        private DeleteCourseAsyncTask(RatingDao ratingDao) {
            this.ratingDao = ratingDao;
        }

        @Override
        protected Void doInBackground(Rating... models) {
            // below line is use to delete
            // our course modal in dao.
            ratingDao.delete(models[0]);
            return null;
        }
    }
}
