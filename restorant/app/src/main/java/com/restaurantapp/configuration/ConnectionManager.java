package com.restaurantapp.configuration;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.restaurantapp.dao.CategoryDao;
import com.restaurantapp.dao.OfferDao;
import com.restaurantapp.dao.OrderDao;
import com.restaurantapp.dao.RestaurantDao;
import com.restaurantapp.dao.UserDao;
import com.restaurantapp.models.Category;
import com.restaurantapp.models.Offer;
import com.restaurantapp.models.Order;
import com.restaurantapp.models.Restaurant;
import com.restaurantapp.models.User;

@Database(entities = {Category.class, Offer.class, Order.class, Restaurant.class, User.class}, version = 2, exportSchema = false)
public abstract class ConnectionManager extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract OfferDao offerDao();
    public abstract OrderDao orderDao();
    public abstract RestaurantDao restaurantDao();
    public abstract UserDao userDao();

    private static volatile ConnectionManager instance;
    public static ConnectionManager getInstance(final Context context) {
        if (instance == null) {
            synchronized (ConnectionManager.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    ConnectionManager.class, "database-name")
                            .fallbackToDestructiveMigration()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    // seed the database with initial data
                                    new SeedDatabaseAsyncTask(instance).execute();
                                }
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                    // enable foreign key constraints
                                    db.execSQL("PRAGMA foreign_keys=ON;");
                                }
                            })
                            .build();
                }
            }
        }
        return instance;
    }

    private static class SeedDatabaseAsyncTask extends AsyncTask<Void, Void, Void> {
        public CategoryDao categoryDao;
        public OfferDao offerDao;
        public OrderDao orderDao;
        public RestaurantDao restaurantDao;
        public UserDao userDao;
        private SeedDatabaseAsyncTask(ConnectionManager db) {
            categoryDao = db.categoryDao();
            offerDao = db.offerDao();
            orderDao = db.orderDao();
            restaurantDao = db.restaurantDao();
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}