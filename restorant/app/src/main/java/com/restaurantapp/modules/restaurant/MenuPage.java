package com.restaurantapp.modules.restaurant;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.restaurantapp.R;
import com.restaurantapp.models.Adapter;
import com.restaurantapp.models.CardModel;

import java.util.ArrayList;
import java.util.List;

public class MenuPage extends AppCompatActivity {
    ViewPager viewPager;
    Adapter adapter;
    List<CardModel> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    BottomNavigationView profile;

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
        models.add(new CardModel(R.drawable.ic_order, "Brochure","Brochure", "Brochure is an informative paper document (often also used for advertising) that can be folded into a template"));
        models.add(new CardModel(R.drawable.ic_money, "Sticker","Sticker", "Sticker is a type of label: a piece of printed paper, plastic, vinyl, or other material with pressure sensitive adhesive on one side"));
        models.add(new CardModel(R.drawable.ic_earth, "Poster","Poster", "Poster is any piece of printed paper designed to be attached to a wall or vertical surface."));
        models.add(new CardModel(R.drawable.ic_offer, "Namecard","Namecard", "Business cards are cards bearing business information about a company or individual."));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        colors = new Integer[]{
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };

        viewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener() {
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
