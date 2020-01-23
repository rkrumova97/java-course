package com.restaurantapp.modules.restaurant;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.restaurantapp.R;
import com.restaurantapp.dao.OfferDao;
import com.restaurantapp.dao.impl.OfferDaoImpl;
import com.restaurantapp.models.Adapter;
import com.restaurantapp.models.CardModel;
import com.restaurantapp.models.Offer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuPage extends AppCompatActivity {
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
        setContentView(R.layout.menu_page_r);

        profile = findViewById(R.id.bottom_navigation);
        profile.setOnNavigationItemSelectedListener(i -> {
            switch (i.getItemId()) {
                case R.id.user:
                    goToProfile(i);
                    return true;
                case R.id.offer:
                    goToOffer(i);
                    return true;
                case R.id.order:
                    goToOrder(i);
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

                    adapter = new Adapter(models, MenuPage.this);

                    viewPager = findViewById(R.id.viewPager);
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

        newoff = findViewById(R.id.btnOrder);
        newoff.setOnClickListener(i -> startActivity(new Intent(this, OfferPage.class)));
    }

    public void goToProfile(MenuItem item) {
        startActivity(new Intent(this, ProfilePage.class));
    }

    public void goToOrder(MenuItem item) {
        startActivity(new Intent(this, OrderPage.class));
    }

    public void goToOffer(MenuItem item) {
        startActivity(new Intent(this, MenuPage.class));
    }

}
