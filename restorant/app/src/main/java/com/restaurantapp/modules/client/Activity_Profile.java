package com.restaurantapp.modules.client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.restaurantapp.R;
import com.restaurantapp.dao.UserDao;
import com.restaurantapp.dao.impl.UserDaoImpl;
import com.restaurantapp.models.User;

public class Activity_Profile extends AppCompatActivity {

    private TextView name;
    private TextView username;
    private TextView email;
    BottomNavigationView profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__profile);

        name = findViewById(R.id.tv_name);
        username = findViewById(R.id.tv_username);
        email = findViewById(R.id.tv_email);

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
        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String emailKey = preferences.getString("emailKey", "");
        final User[] user = {new User()};
        Thread thread = new Thread() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try {
                    UserDao userDao = new UserDaoImpl();
                    user[0] = userDao.readUser(emailKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> {
                    name.setText(user[0].getFirstName()+ " " +user[0].getLastName());
                    username.setText(user[0].getUsername());
                    email.setText(user[0].getEmail());
                });
            }

        };
        thread.start();
    }

    public void goToProfile(MenuItem item) {
        startActivity(new Intent(this, Activity_Profile.class));
    }

    public void goToOrders(MenuItem item) {
        startActivity(new Intent(this, Activity_Orders.class));
    }

    public void goToMore(MenuItem item) {

        startActivity(new Intent(this, Activity_More.class));
    }

}