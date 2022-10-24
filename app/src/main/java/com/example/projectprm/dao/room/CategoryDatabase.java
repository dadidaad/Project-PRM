package com.example.projectprm.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projectprm.dao.CategoryDao;
import com.example.projectprm.model.entities.Category;

@Database(entities = {Category.class}, version = 1, exportSchema = false)
public abstract class CategoryDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "script.db";

    public abstract CategoryDao categoryDao();

    private static CategoryDatabase INSTANCE = null;

    public static CategoryDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (CategoryDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                CategoryDatabase.class, DATABASE_NAME)
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
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(CategoryDatabase instance) {
            CategoryDao dao = instance.categoryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
