package com.restaurantapp.modules.restaurant;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.restaurantapp.R;

public class HomePage extends AppCompatActivity {
    MenuItem profile;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_restaurant);

        profile = findViewById(R.id.user);

    }

    protected void goToProfile(){
        startActivity(new Intent(this, ProfilePage.class));
    }


}
