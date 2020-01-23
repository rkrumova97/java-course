package com.restaurantapp.modules.client;

import android.animation.ArgbEvaluator;
import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.restaurantapp.R;
import com.restaurantapp.models.Adapter;

import java.util.List;

public class Activity_HomePage_Client extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<ColorSpace.Model> models;
    Integer[] colors =null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__home_page__client);
    }
}
