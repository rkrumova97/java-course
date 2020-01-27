package com.restaurantapp.modules.client;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.restaurantapp.dao.OfferDao;
import com.restaurantapp.dao.impl.OfferDaoImpl;
import com.restaurantapp.models.Adapter;
import com.restaurantapp.models.CardModel;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Button;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.restaurantapp.R;
import com.restaurantapp.models.Offer;
import com.restaurantapp.modules.restaurant.OfferPage;

public class Activity_Orders extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<CardModel> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    BottomNavigationView profile;
    Button newoff;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__orders);

        profile = findViewById(R.id.bottom_navigation_client);
        profile.setOnNavigationItemSelectedListener(i -> {
            switch (i.getItemId()) {
                case R.id.discover:
                    goToDiscover(i);
                    return true;
                case R.id.more:
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

        Thread thread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    resID[0] = fields[rndInt].getInt(drawableClass);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                OfferDao offerDao = new OfferDaoImpl();
                List<Offer> offers = new ArrayList<>();
                try {
                    offers = offerDao.readAllOffer();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                List<Offer> finalOffers = offers;
                runOnUiThread(() ->{
                    int finalResID = resID[0];
                    finalOffers.forEach(i -> models.add(new CardModel(finalResID, i.getText(), i.getPrice().toString(), i.getText())));

                    adapter = new Adapter(models, Activity_Orders.this);

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
                    newoff = findViewById(R.id.button_see);
                    newoff.setOnClickListener(i -> startActivity(new Intent(Activity_Orders.this, OfferPage.class)));
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

    public void goToDiscover(MenuItem item) {
        startActivity(new Intent(this, Activity_Orders.class));
    }

    public void goToMore(MenuItem item) {
        startActivity(new Intent(this, Activity_Welcome.class));
    }

}
