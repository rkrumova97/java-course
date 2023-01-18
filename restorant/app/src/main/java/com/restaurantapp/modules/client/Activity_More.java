package com.restaurantapp.modules.client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.restaurantapp.Activity_Login;
import com.restaurantapp.R;

@SuppressLint("Registered")
public class Activity_More extends AppCompatActivity {

    BottomNavigationView more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__more);

        Button b1 = findViewById(R.id.b1_more);
        b1.setOnClickListener(v -> openActivity_AboutUs());

        Button b2 = findViewById(R.id.b2_more);
        b2.setOnClickListener(v -> openActivity_ContactUs());

        Button b3 = findViewById(R.id.logout1);
        b3.setOnClickListener(v -> logout());


        more = findViewById(R.id.bottom_navigation_client);
        more.setOnNavigationItemSelectedListener(i -> {
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
    }

        private void openActivity_ContactUs() {
            Intent intent = new Intent(this, Activity_ContactUs.class);
            startActivity(intent);
        }

        private void openActivity_AboutUs() {
            Intent intent = new Intent(this, Activity_AboutUs.class);
            startActivity(intent);
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

    public  void logout(){
        SharedPreferences sharedpreferences = getSharedPreferences(Activity_Login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(this, Activity_Login.class));
    }

}
