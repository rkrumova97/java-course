package com.restaurantapp.modules.restaurant;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.restaurantapp.R;

public class HomePage extends AppCompatActivity {
    MaterialCardView materialCardView1;
    MaterialCardView materialCardView2;
    MaterialCardView materialCardView3;


    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_restaurant);

        materialCardView1 = findViewById(R.id.card_view1);
        materialCardView1.setOnClickListener((i) -> startActivity(new Intent(this, ProfilePage.class)));

        materialCardView2 = findViewById(R.id.card_view2);
        materialCardView2.setOnClickListener((i) -> startActivity(new Intent(this, OrderPage.class)));

        materialCardView3 = findViewById(R.id.card_view3);
        materialCardView3.setOnClickListener((i) -> startActivity(new Intent(this, MenuPage.class)));

    }


}
