package com.example.projectprm.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projectprm.dao.AuthorDao;
import com.example.projectprm.model.entities.Author;

@Database(entities = {Author.class}, version = 1, exportSchema = false)
public abstract class AuthorDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "script.db";

    public abstract AuthorDao authorDao();

    private static AuthorDatabase INSTANCE = null;

    public static AuthorDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (BookDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AuthorDatabase.class, DATABASE_NAME)
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
            new AuthorDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(AuthorDatabase instance) {
            AuthorDao dao = instance.authorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
