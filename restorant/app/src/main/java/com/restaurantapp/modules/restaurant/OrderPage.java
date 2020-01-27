package com.restaurantapp.modules.restaurant;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.restaurantapp.R;
import com.restaurantapp.dao.OrderDao;
import com.restaurantapp.dao.impl.OrderDaoImpl;
import com.restaurantapp.models.Adapter;
import com.restaurantapp.models.ListAdapter;
import com.restaurantapp.models.ListModel;
import com.restaurantapp.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderPage extends AppCompatActivity {
    BottomNavigationView profile;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_page_r);

        OrderDao orderDao = new OrderDaoImpl();
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


        new Thread(() -> {
            List<Order> orderList = new ArrayList<>();
            List<ListModel> models = new ArrayList<>();
            try {
                orderList = orderDao.readAllOrder();
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<Order> finalOrderList = orderList;
            runOnUiThread(() -> {
                ListView listView = findViewById(R.id.listView);
                finalOrderList.forEach(i -> models.add(new ListModel(i.getUser().getFirstName(), i.getUser().getLastName(), i.getOffer().getTitle(), i.getLocalDateTime().toString())));
                ListAdapter listAdapter = new
                        ListAdapter(models, this);
                listView.setAdapter(listAdapter);
            });
        }).start();

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
