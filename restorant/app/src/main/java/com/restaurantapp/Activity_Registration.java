package com.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.restaurantapp.modules.client.Activity_ContactUs;


public class  Activity_Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__registration);

        Button b = findViewById(R.id.b_to_preferences);
        b.setOnClickListener(v -> openActivity_Preferences());
    }

    private void openActivity_Preferences() {
        Intent intent = new Intent(this, Activity_Preferences.class);
        startActivity(intent);
    }
}
