package com.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Preferences extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__preferences);

        Button bt = findViewById(R.id.bt);
        bt.setOnClickListener(v -> openActivity_Login());
    }

    private void openActivity_Login() {
        Intent intent = new Intent(this, Activity_Login.class);
        startActivity(intent);
    }
}
