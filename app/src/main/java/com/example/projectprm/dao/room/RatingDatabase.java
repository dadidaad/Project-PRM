package com.example.projectprm.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projectprm.dao.RatingDao;
import com.example.projectprm.model.entities.Author;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.Order;
import com.example.projectprm.model.entities.Rating;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.utils.converters.DateConverter;

@Database(entities = {Rating.class, Order.class, Account.class, Book.class, Author.class, Category.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class RatingDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "script.db";

    public abstract RatingDao ratingDao();

    private static RatingDatabase INSTANCE = null;

    public static RatingDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (RatingDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                RatingDatabase.class, DATABASE_NAME)
                        .createFromAsset("databases/script.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }

    // below line is to create a callback for our room database.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // this method is called when database is created
            // and below line is to populate our data.
            new RatingDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(RatingDatabase instance) {
            RatingDao dao = instance.ratingDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
