package com.example.projectprm.model.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projectprm.dao.BookDAO;
import com.example.projectprm.dao.CategoryDao;
import com.example.projectprm.dao.room.BookDatabase;
import com.example.projectprm.dao.room.CategoryDatabase;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Category;

import java.util.List;

public class BookRepository {
    private BookDAO bookDAO;

    private List<Book> allBook;

    private List<Book> highestRatBook;
    private List<Book> listGetByCategory;

    public BookRepository() {
    }

    public BookRepository(Application application) {
        BookDatabase categoryDatabase = BookDatabase.getINSTANCE(application);
        bookDAO = categoryDatabase.bookDAO();
        allBook = bookDAO.getAll();
        highestRatBook = bookDAO.getBookOrderRate();
    }

    public void insert(Book model) {
        new BookRepository.InsertCourseAsyncTask(bookDAO).execute(model);
    }

    // creating a method to update data in database.
    public void update(Book model) {
        new BookRepository.UpdateCourseAsyncTask(bookDAO).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(Book model) {
        new BookRepository.DeleteCourseAsyncTask(bookDAO).execute(model);
    }
    public void insert(Application application,Book book) {
        BookDatabase Database = BookDatabase.getINSTANCE(application);
        bookDAO = Database.bookDAO();
        bookDAO.insert(book);
    }

    // creating a method to update data in database.
    public void update(Application application,Book book) {
        BookDatabase Database = BookDatabase.getINSTANCE(application);
        bookDAO = Database.bookDAO();
        bookDAO.update(book);
    }
    // creating a method to delete the data in our database.
    public void delete(Application application,Book book) {
        BookDatabase Database = BookDatabase.getINSTANCE(application);
        bookDAO = Database.bookDAO();
        bookDAO.delete(book);
    }
    public List<Book> getAll() {
        return allBook;
    }
    public Book getByID(int bookId) { return bookDAO.get(bookId);}
    public List<Book> getHighestRatBook(){
        return highestRatBook;
    }
    public List<Book> getListGetByCategory(int catID){
        return bookDAO.getBookByCategory(catID);
    }

    private static class InsertCourseAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDAO bookDAO;

        private InsertCourseAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... model) {
            // below line is use to insert our modal in dao.
            bookDAO.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDAO bookDAO;

        private UpdateCourseAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... models) {
            // below line is use to update
            // our modal in dao.
            bookDAO.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<Book, Void, Void> {
        private BookDAO bookDAO;

        private DeleteCourseAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... models) {
            // below line is use to delete
            // our course modal in dao.
            bookDAO.delete(models[0]);
            return null;
        }
    }
}