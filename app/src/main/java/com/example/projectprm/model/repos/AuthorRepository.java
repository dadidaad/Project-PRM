package com.example.projectprm.model.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projectprm.dao.AuthorDao;
import com.example.projectprm.dao.BookDAO;
import com.example.projectprm.dao.room.AuthorDatabase;
import com.example.projectprm.dao.room.BookDatabase;
import com.example.projectprm.model.entities.Author;
import com.example.projectprm.model.entities.Book;

import java.util.List;

public class AuthorRepository {
    private AuthorDao authorDao;
    private List<Author> authors;
    private Author author;

    public AuthorRepository(Application application) {
        AuthorDatabase authorDatabase = AuthorDatabase.getINSTANCE(application);
        authorDao = authorDatabase.authorDao();
        authors = authorDao.getAll();
    }

    public void insert(Author model) {
        new AuthorRepository.InsertCourseAsyncTask(authorDao).execute(model);
    }

    // creating a method to update data in database.
    public void update(Author model) {
        new AuthorRepository.UpdateCourseAsyncTask(authorDao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(Author model) {
        new AuthorRepository.DeleteCourseAsyncTask(authorDao).execute(model);
    }

    public Author get(int id){
        return authorDao.get(id);
    }
    public List<Author> getAll() {
        return authors;
    }
    public Author getAuthorById(int authorId){return authorDao.get(authorId);}

    private static class InsertCourseAsyncTask extends AsyncTask<Author, Void, Void> {
        private AuthorDao authorDao;

        private InsertCourseAsyncTask(AuthorDao authorDao) {
            this.authorDao = authorDao;
        }

        @Override
        protected Void doInBackground(Author... model) {
            // below line is use to insert our modal in dao.
            authorDao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<Author, Void, Void> {
        private AuthorDao authorDao;

        private UpdateCourseAsyncTask(AuthorDao authorDao) {
            this.authorDao = authorDao;
        }

        @Override
        protected Void doInBackground(Author... models) {
            // below line is use to update
            // our modal in dao.
            authorDao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<Author, Void, Void> {
        private AuthorDao authorDao;

        private DeleteCourseAsyncTask(AuthorDao authorDao) {
            this.authorDao = authorDao;
        }

        @Override
        protected Void doInBackground(Author... models) {
            // below line is use to delete
            // our course modal in dao.
            authorDao.delete(models[0]);
            return null;
        }
    }
}
