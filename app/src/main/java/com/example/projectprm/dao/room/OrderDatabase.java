package com.example.projectprm.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projectprm.dao.OrderDao;
import com.example.projectprm.model.entities.Order;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.utils.converters.DateConverter;

@Database(entities = {Order.class, Account.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class OrderDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "script.db";

    public abstract OrderDao OrderDAO();

    private static OrderDatabase INSTANCE = null;
    public static OrderDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (OrderDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                OrderDatabase.class, DATABASE_NAME)
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
            new OrderDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(OrderDatabase instance) {
            OrderDao dao = instance.OrderDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
