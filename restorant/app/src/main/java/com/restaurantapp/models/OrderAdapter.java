package com.restaurantapp.models;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.snackbar.Snackbar;
import com.restaurantapp.R;
import com.restaurantapp.dao.OrderDao;
import com.restaurantapp.dao.impl.OrderDaoImpl;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderAdapter extends PagerAdapter {

    private List<CardModel> models;
    private Context context;

    public OrderAdapter(List<CardModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        desc.setText(models.get(position).getDesc());

        view.setOnClickListener(v -> new Thread() {
            @Override
            public void run() {
                OrderDao orderDao = new OrderDaoImpl();
                Order order = new Order();
                order.setUser(models.get(position).getUser());
                order.setOffer(models.get(position).getOffer());
                order.setLocalDateTime(LocalDateTime.now());

                try {
                    orderDao.createOrder(order);
                    Snackbar.make(imageView, R.string.saved, Snackbar.LENGTH_SHORT)
                            .show();

                } catch (Exception e) {
                    e.printStackTrace();
                    Snackbar.make(imageView, R.string.error, Snackbar.LENGTH_SHORT)
                            .show();

                }
            }
        }.start());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
