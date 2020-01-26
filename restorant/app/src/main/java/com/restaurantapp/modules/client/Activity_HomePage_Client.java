package com.restaurantapp.modules.client;

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
import com.restaurantapp.models.Adapter;
import com.restaurantapp.models.CardModel;
import com.restaurantapp.modules.restaurant.OfferPage;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Activity_HomePage_Client extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__home_page__client);
    }
}
