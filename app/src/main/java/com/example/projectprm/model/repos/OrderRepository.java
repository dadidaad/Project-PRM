package com.example.projectprm.model.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.example.projectprm.dao.OrderDao;
import com.example.projectprm.dao.OrderDetailDAO;
import com.example.projectprm.dao.room.OrderDatabase;
import com.example.projectprm.dao.room.OrderDetailDatabase;
import com.example.projectprm.model.entities.Order;
import com.example.projectprm.model.entities.OrderDetail;

import java.util.List;

public class OrderRepository {
    private OrderDao orderDAO;

    public OrderRepository(Application application) {
        OrderDatabase orderDatabase = OrderDatabase.getINSTANCE(application);
    }
    public void insert(Order model) {
        new OrderRepository.InsertCourseAsyncTask(orderDAO).execute(model);
    }

    // creating a method to update data in database.
    public void update(Order model) {
        new OrderRepository.UpdateCourseAsyncTask(orderDAO).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(Order model) {
        new OrderRepository.DeleteCourseAsyncTask(orderDAO).execute(model);
    }

    public List<Order> getAll() {return orderDAO.getAll();}
    public List<Order> getById(int orderId) {return orderDAO.getById(orderId);}

    private static class InsertCourseAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDao orderDao;

        private InsertCourseAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... model) {
            // below line is use to insert our modal in dao.
            orderDao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDao orderDao;

        private UpdateCourseAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... models) {
            // below line is use to update
            // our modal in dao.
            orderDao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDao orderDao;

        private DeleteCourseAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... models) {
            // below line is use to delete
            // our course modal in dao.
            orderDao.delete(models[0]);
            return null;
        }
    }
}
