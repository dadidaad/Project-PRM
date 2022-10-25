package com.example.projectprm.dao.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projectprm.dao.CategoryDao;
import com.example.projectprm.dao.PriceDAO;
import com.example.projectprm.model.entities.Category;
import com.example.projectprm.model.entities.Price;
import com.example.projectprm.utils.converters.DateConverter;

@Database(entities = {Price.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class PriceDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "script.db";
    public abstract PriceDAO priceDAO();

    private static PriceDatabase INSTANCE = null;

    public static PriceDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (PriceDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                PriceDatabase.class, DATABASE_NAME)
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
            new PriceDatabase.PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(PriceDatabase instance) {
            PriceDAO dao = instance.priceDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
