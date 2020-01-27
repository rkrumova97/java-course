package com.restaurantapp.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.restaurantapp.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<ListModel> {
    private List<ListModel> models;
    private Context context;

    public ListAdapter(List<ListModel> models, Context
            context) {
        super(context, R.layout.order, models);
        this.models = models;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.order, parent, false);

        TextView firstName, lastName, title, time;

        title = view.findViewById(R.id.off);
        firstName = view.findViewById(R.id.fname);
        lastName = view.findViewById(R.id.lname);
        time = view.findViewById(R.id.time);

        title.setText(models.get(position).getOffer());
        firstName.setText(models.get(position).getFirstName());
        lastName.setText(models.get(position).getLastName());
        time.setText(models.get(position).getTime());

        return view;
    }

}
