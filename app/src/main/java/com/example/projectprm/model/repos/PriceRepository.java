package com.example.projectprm.model.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projectprm.dao.CategoryDao;
import com.example.projectprm.dao.PriceDAO;
import com.example.projectprm.dao.room.CategoryDatabase;
import com.example.projectprm.dao.room.PriceDatabase;
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.Price;

import java.util.List;

public class PriceRepository {
    private PriceDAO priceDAO;

    private List<Price> allPrice;
    private List<Price> allPriceOrder;
    private Price priceByBookId;

    public PriceRepository(Application application) {
        PriceDatabase priceDatabase = PriceDatabase.getINSTANCE(application);
        priceDAO = priceDatabase.priceDAO();
        allPrice = priceDAO.getAll();
        allPriceOrder = priceDAO.getOrderByPrice();
    }
    public void insert(Price model) {
        new PriceRepository.InsertCourseAsyncTask(priceDAO).execute(model);
    }

    // creating a method to update data in database.
    public void update(Price model) {
        new PriceRepository.UpdateCourseAsyncTask(priceDAO).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(Price model) {
        new PriceRepository.DeleteCourseAsyncTask(priceDAO).execute(model);
    }
    public List<Price> getAll() {
        return allPrice;
    }
    public List<Price> getAllPriceOrder() {
        return allPriceOrder;
    }
    public Price getPriceBookID(int bookId){return priceDAO.getByBookID(bookId);}
    private static class InsertCourseAsyncTask extends AsyncTask<Price, Void, Void> {
        private PriceDAO priceDAO;

        private InsertCourseAsyncTask(PriceDAO priceDAO) {
            this.priceDAO = priceDAO;
        }

        @Override
        protected Void doInBackground(Price... model) {
            // below line is use to insert our modal in dao.
            priceDAO.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<Price, Void, Void> {
        private PriceDAO priceDao;

        private UpdateCourseAsyncTask(PriceDAO priceDAO) {
            this.priceDao = priceDAO;
        }

        @Override
        protected Void doInBackground(Price... models) {
            // below line is use to update
            // our modal in dao.
            priceDao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<Price, Void, Void> {
        private PriceDAO priceDAO;

        private DeleteCourseAsyncTask(PriceDAO priceDAO) {
            this.priceDAO = priceDAO;
        }

        @Override
        protected Void doInBackground(Price... models) {
            // below line is use to delete
            // our course modal in dao.
            priceDAO.delete(models[0]);
            return null;
        }
    }
}
