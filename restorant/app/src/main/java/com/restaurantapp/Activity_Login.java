package com.restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.restaurantapp.dao.UserDao;
import com.restaurantapp.dao.impl.UserDaoImpl;
import com.restaurantapp.models.Role;
import com.restaurantapp.models.User;
import com.restaurantapp.modules.client.Activity_Welcome;
import com.restaurantapp.modules.restaurant.HomePage;

import java.util.List;
import java.util.stream.Collectors;


public class Activity_Login extends AppCompatActivity {

    LinearLayout linearLayout;
        RelativeLayout relativeLayout;

    Button b1, b2;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailKey";
    public static final String Password = "passwordKey";
    SharedPreferences sharedpreferences;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relativeLayout.setVisibility(View.VISIBLE);
        }
    };


    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linearLayout = findViewById(R.id.linlay1);
        relativeLayout = findViewById(R.id.rel1);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        b1 = findViewById(R.id.login);
        b1.setOnClickListener(v -> new Thread(() -> {
            try {
                UserDao userDao = new UserDaoImpl();
                List<User> userList = userDao.readAllUser();
                EditText password = findViewById(R.id.password);
                EditText username = findViewById(R.id.username);

                String ps = password.getText().toString();
                String us = username.getText().toString();
                List<User> collect = userList.stream().filter(u -> u.getPassword().equals(ps) && u.getEmail().equals(us)).collect(Collectors.toList());
                if (collect.size() == 1) {
                    if(collect.get(0).getRole().equals(Role.EMPLOYEE)) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Password, ps);
                        editor.putString(Email, us);
                        editor.apply();

                        Intent intent = new Intent(Activity_Login.this, HomePage.class);
                        startActivity(intent);
                    } else if(collect.get(0).getRole().equals(Role.CLIENT)){
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Password, ps);
                        editor.putString(Email, us);
                        editor.apply();

                        Intent intent = new Intent(Activity_Login.this, Activity_Welcome.class);
                        startActivity(intent);
                    }
                } else {
                    View contextView = relativeLayout;

                    Snackbar.make(contextView, R.string.wrong_credentials, Snackbar.LENGTH_SHORT)
                            .show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());

        b2 = findViewById(R.id.register);
        b2.setOnClickListener(v ->
                startActivity(new Intent(this, Activity_Registration.class)));
    }
}
