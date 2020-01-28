package com.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Activity_PasswordForgotten extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__password_forgotten);

        Button btn = findViewById(R.id.b_to_login);
        btn.setOnClickListener(v -> openActivity_Login());
    }

    private void openActivity_Login() {
        Intent intent = new Intent(this, Activity_Login.class);
        startActivity(intent);
    }
}
