package com.restaurantapp.modules.client;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.restaurantapp.R;
import com.restaurantapp.configuration.ConnectionManager;
import com.restaurantapp.dao.OfferDao;
import com.restaurantapp.dao.UserDao;
import com.restaurantapp.models.CardModel;
import com.restaurantapp.models.Offer;
import com.restaurantapp.models.OrderAdapter;
import com.restaurantapp.models.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class  Activity_Orders extends AppCompatActivity {

    ViewPager viewPager;
    OrderAdapter adapter;
    List<CardModel> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    BottomNavigationView profile;
    private ConnectionManager connectionManager;

    public Activity_Orders(){
            connectionManager = Room.databaseBuilder(this, ConnectionManager.class, "restaurant").build();
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__orders);

        profile = findViewById(R.id.bottom_navigation_client);
        profile.setOnNavigationItemSelectedListener(i -> {
            switch (i.getItemId()) {
                case R.id.nav_profile:
                    goToProfile(i);
                    return true;
                case R.id.nav_store:
                    goToOrders(i);
                    return true;
                case R.id.nav_more:
                    goToMore(i);
                    return true;
                default:
                    return super.onOptionsItemSelected(i);
            }

        });

        models = new ArrayList<>();
        final Class drawableClass = R.drawable.class;
        final Field[] fields = drawableClass.getFields();
        final Random rand = new Random();
        int rndInt = rand.nextInt(fields.length);
        final int[] resID = {0};

        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String emailKey = preferences.getString("emailKey", "");

        Thread thread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                User user = new User();
                try {
                    UserDao userDao = connectionManager.userDao();
                    user = userDao.readUser(emailKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    resID[0] = fields[rndInt].getInt(drawableClass);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                OfferDao offerDao = connectionManager.offerDao();
                List<Offer> offers = new ArrayList<>();
                try {
                    offers = offerDao.readAllOffer();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                List<Offer> finalOffers = offers;
                User finalUser = user;
                runOnUiThread(() -> {
                    int finalResID = resID[0];
                    finalOffers.forEach(i -> models.add(new CardModel(finalResID, i.getText(), i.getPrice().toString(), i.getText(), i, finalUser)));

                    adapter = new OrderAdapter(models, Activity_Orders.this, connectionManager);

                    viewPager = findViewById(R.id.viewPager_recommended);
                    viewPager.setAdapter(adapter);
                    viewPager.setPadding(130, 0, 130, 0);

                    colors = new Integer[]{
                            getResources().getColor(R.color.color1),
                            getResources().getColor(R.color.color2),
                            getResources().getColor(R.color.color3),
                            getResources().getColor(R.color.color4),
                            getResources().getColor(R.color.colorPrimary),
                            getResources().getColor(R.color.colorPrimaryDark)
                    };

                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                                viewPager.setBackgroundColor(

                                        (Integer) argbEvaluator.evaluate(
                                                positionOffset,
                                                colors[position],
                                                colors[position + 1]
                                        )
                                );
                            } else {
                                viewPager.setBackgroundColor(colors[colors.length - 1]);
                            }
                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });

                });


                Looper.loop();
            }
        };
        thread.start();


    }

    public void goToProfile (MenuItem item) {
        startActivity(new Intent(this, Activity_Profile.class));
    }

    public void goToOrders(MenuItem item) {
        startActivity(new Intent(this, Activity_Orders.class));
    }

    public void goToMore(MenuItem item) {
        startActivity(new Intent(this, Activity_More.class));
    }

}
