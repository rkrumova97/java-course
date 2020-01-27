package com.restaurantapp.modules.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.restaurantapp.R;

public class Activity_Welcome extends AppCompatActivity {
    private Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__welcome);

        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(v -> openActivity_Profile());

        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(v -> openActivity_Orders());

        b3 = findViewById(R.id.b3);
        b3.setOnClickListener(v -> openActivity_ContactUs());
    }

    public void openActivity_Profile() {
        Intent intent = new Intent(this, Activity_Profile.class);
        startActivity(intent);
    }

    public void openActivity_Orders() {
        Intent intent = new Intent(this, Activity_Orders.class);
        startActivity(intent);
    }

    public void  openActivity_ContactUs(){
        Intent intent = new Intent(this, Activity_ContactUs.class);
        startActivity(intent);
    }
}