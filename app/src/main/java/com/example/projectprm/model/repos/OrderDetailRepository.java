package com.example.projectprm.model.repos;


import android.app.Application;
import android.os.AsyncTask;

import com.example.projectprm.dao.BookDAO;
import com.example.projectprm.dao.OrderDetailDAO;

import com.example.projectprm.dao.room.BookDatabase;
import com.example.projectprm.dao.room.OrderDetailDatabase;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.OrderDetail;

import java.util.List;

public class OrderDetailRepository {
    private OrderDetailDAO orderDetailDAO;
    private List<OrderDetail> allOrderDetails;
    private List<Integer> listBestSellingBookID;

    public OrderDetailRepository(Application application) {
        OrderDetailDatabase orderDetailDatabase = OrderDetailDatabase.getINSTANCE(application);
        orderDetailDAO = orderDetailDatabase.orderDetailDAO();
        allOrderDetails = orderDetailDAO.getAll();
        listBestSellingBookID = orderDetailDAO.getBestSellingBookID();
    }

    public void insert(OrderDetail model) {
        new OrderDetailRepository.InsertCourseAsyncTask(orderDetailDAO).execute(model);
    }

    // creating a method to update data in database.
    public void update(OrderDetail model) {
        new OrderDetailRepository.UpdateCourseAsyncTask(orderDetailDAO).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(OrderDetail model) {
        new OrderDetailRepository.DeleteCourseAsyncTask(orderDetailDAO).execute(model);
    }
    public List<OrderDetail> getAll() {
        return allOrderDetails;
    }
    public List<Integer> getListBestSellingBookID(){return listBestSellingBookID;}


    private static class InsertCourseAsyncTask extends AsyncTask<OrderDetail, Void, Void> {
        private OrderDetailDAO orderDetailDAO;

        private InsertCourseAsyncTask(OrderDetailDAO orderDetailDAO) {
            this.orderDetailDAO = orderDetailDAO;
        }

        @Override
        protected Void doInBackground(OrderDetail... model) {
            // below line is use to insert our modal in dao.
            orderDetailDAO.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<OrderDetail, Void, Void> {
        private OrderDetailDAO orderDetailDAO;

        private UpdateCourseAsyncTask(OrderDetailDAO orderDetailDAO) {
            this.orderDetailDAO = orderDetailDAO;
        }

        @Override
        protected Void doInBackground(OrderDetail... models) {
            // below line is use to update
            // our modal in dao.
            orderDetailDAO.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<OrderDetail, Void, Void> {
        private OrderDetailDAO orderDetailDAO;

        private DeleteCourseAsyncTask(OrderDetailDAO orderDetailDAO) {
            this.orderDetailDAO = orderDetailDAO;
        }

        @Override
        protected Void doInBackground(OrderDetail... models) {
            // below line is use to delete
            // our course modal in dao.
            orderDetailDAO.delete(models[0]);
            return null;
        }
    }
}
