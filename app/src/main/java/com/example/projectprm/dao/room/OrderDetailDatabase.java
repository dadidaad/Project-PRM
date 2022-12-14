package com.example.projectprm.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projectprm.dao.BookDAO;
import com.example.projectprm.dao.OrderDetailDAO;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.model.entities.Author;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.Order;
import com.example.projectprm.model.entities.OrderDetail;
import com.example.projectprm.utils.converters.DateConverter;

@Database(entities = {Book.class, OrderDetail.class, Author.class, Category.class, Order.class, Account.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class OrderDetailDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "script.db";

    public abstract OrderDetailDAO orderDetailDAO();

    private static OrderDetailDatabase INSTANCE = null;
    public static OrderDetailDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (OrderDetailDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                OrderDetailDatabase.class, DATABASE_NAME)
                        .createFromAsset("databases/script.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // this method is called when database is created
            // and below line is to populate our data.
            new OrderDetailDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(OrderDetailDatabase instance) {
            OrderDetailDAO dao = instance.orderDetailDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
