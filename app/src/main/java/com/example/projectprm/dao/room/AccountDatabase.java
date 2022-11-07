package com.example.projectprm.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projectprm.dao.AccountDao;
import com.example.projectprm.model.entities.Account;
import com.example.projectprm.utils.converters.DateConverter;
import com.example.projectprm.utils.converters.IntConverter;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AccountDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "script.db";

    public abstract AccountDao accountDao();

    private static AccountDatabase INSTANCE = null;

    public static AccountDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (BookDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AccountDatabase.class, DATABASE_NAME)
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
            new AccountDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(AccountDatabase instance) {
            AccountDao dao = instance.accountDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
