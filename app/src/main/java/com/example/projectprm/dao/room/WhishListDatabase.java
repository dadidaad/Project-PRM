package com.example.projectprm.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.projectprm.dao.AccountDao;
import com.example.projectprm.dao.WhishListDao;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.model.entities.Author;
import com.example.projectprm.model.entities.Book;
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.WhishList;
import com.example.projectprm.utils.converters.DateConverter;

@Database(entities = {WhishList.class, Account.class, Book.class, Author.class, Category.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class WhishListDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "script.db";

    public abstract WhishListDao whishListDao();

    private static WhishListDatabase INSTANCE = null;

    public static WhishListDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (WhishListDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                WhishListDatabase.class, DATABASE_NAME)
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
            new WhishListDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(WhishListDatabase instance) {
            WhishListDao dao = instance.whishListDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
