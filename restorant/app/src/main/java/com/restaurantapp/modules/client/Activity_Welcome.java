package com.restaurantapp.modules.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.restaurantapp.R;

public class Activity_Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__welcome);

        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener(v -> openActivity_Profile());

        Button b2 = findViewById(R.id.b2);
        b2.setOnClickListener(v -> openActivity_Orders());

        Button b3 = findViewById(R.id.b3);
        b3.setOnClickListener(v -> openActivity_More());
    }

    public void openActivity_Profile() {
        Intent intent = new Intent(this, Activity_Profile.class);
        startActivity(intent);
    }

    public void openActivity_Orders() {
        Intent intent = new Intent(this, Activity_Orders.class);
        startActivity(intent);
    }

    public void  openActivity_More(){
        Intent intent = new Intent(this, Activity_More.class);
        startActivity(intent);
    }
}