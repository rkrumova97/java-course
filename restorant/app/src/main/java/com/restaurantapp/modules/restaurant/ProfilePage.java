package com.restaurantapp.modules.restaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.restaurantapp.R;
import com.restaurantapp.dao.RestaurantDao;
import com.restaurantapp.dao.UserDao;
import com.restaurantapp.dao.impl.RestaurantDaoImpl;
import com.restaurantapp.dao.impl.UserDaoImpl;
import com.restaurantapp.models.Restaurant;
import com.restaurantapp.models.User;

public class ProfilePage extends AppCompatActivity {
    BottomNavigationView profile;
    Button save;
    EditText firstName;
    EditText lastName;
    EditText restaurantName;
    EditText restaurantAddress;
    EditText email;
    Switch aSwitch;
    LinearLayout lin1;
    LinearLayout lin2;
    LinearLayout lin3;
    View view;
    UserDao userDao;
    RestaurantDao restaurantDao;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page_r);
        restaurantDao = new RestaurantDaoImpl();
        userDao = new UserDaoImpl();
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

        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String emailKey = preferences.getString("emailKey", "");
        final User[] user = {new User()};
        Thread thread = new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    user[0] = userDao.readUser(emailKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> {
                    firstName = findViewById(R.id.first_name);
                    firstName.setText(user[0].getFirstName());

                    lastName = findViewById(R.id.lastnm);
                    lastName.setText(user[0].getLastName());

                    email = findViewById(R.id.maill);
                    email.setText(emailKey);

                    lin1 = findViewById(R.id.lay1);
                    lin2 = findViewById(R.id.lay2);
                    lin3 = findViewById(R.id.lay3);
                    view = findViewById(R.id.lin1);


                    lin1.setVisibility(View.INVISIBLE);
                    lin2.setVisibility(View.INVISIBLE);
                    lin3.setVisibility(View.INVISIBLE);
                    view.setVisibility(View.INVISIBLE);

                    restaurantName = findViewById(R.id.goooglggeee);
                    restaurantName.setText(user[0].getRestaurant().getName());
                    restaurantAddress = findViewById(R.id.address);
                    restaurantAddress.setText(user[0].getRestaurant().getAddress());

                });

                Looper.loop();
            }
        };
        thread.start();


        aSwitch = findViewById(R.id.switcch);
        aSwitch.setOnClickListener(i -> {
            if (aSwitch.isChecked()) {
                lin1.setVisibility(View.VISIBLE);
                lin2.setVisibility(View.VISIBLE);
                lin3.setVisibility(View.VISIBLE);
                view.setVisibility(View.VISIBLE);
            } else {
                lin1.setVisibility(View.INVISIBLE);
                lin2.setVisibility(View.INVISIBLE);
                lin3.setVisibility(View.INVISIBLE);
                view.setVisibility(View.INVISIBLE);

            }
        });

        save = findViewById(R.id.save);
        save.setOnClickListener(i -> new Thread(() -> {
            user[0].setFirstName(firstName.getText().toString());
            user[0].setLastName(lastName.getText().toString());
            user[0].setEmail(email.getText().toString());
            Restaurant restaurant = user[0].getRestaurant();
            restaurant.setName(restaurantName.getText().toString());
            restaurant.setAddress(restaurantAddress.getText().toString());
            restaurantDao.updateRestaurant(restaurant);
            userDao.updateUser(user[0]);

            Snackbar.make(profile, R.string.saved, Snackbar.LENGTH_SHORT)
                    .show();
        }).start());
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
