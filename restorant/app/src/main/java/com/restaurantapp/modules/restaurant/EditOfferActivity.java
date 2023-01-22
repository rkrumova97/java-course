package com.restaurantapp.modules.restaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.restaurantapp.R;
import com.restaurantapp.configuration.ConnectionManager;
import com.restaurantapp.dao.CategoryDao;
import com.restaurantapp.dao.OfferDao;
import com.restaurantapp.dao.UserDao;
import com.restaurantapp.models.Category;
import com.restaurantapp.models.Offer;
import com.restaurantapp.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class EditOfferActivity extends AppCompatActivity {
    BottomNavigationView profile;
    Button save;
    EditText price;
    EditText text;
    EditText title;
    private ChipGroup chipsPrograms;
    String category;
    private ConnectionManager connectionManager;

    public EditOfferActivity(){
        connectionManager = Room.databaseBuilder(this, ConnectionManager.class, "restaurant").build();
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offer);
        Long offerIdId = Long.parseLong(Objects.requireNonNull(getIntent().getStringExtra("offer")));
        final Offer[] offer = {new Offer()};
        chipsPrograms = findViewById(R.id.chipsPrograms);
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

        UserDao userDao = connectionManager.userDao();
        OfferDao offerDao = connectionManager.offerDao();
        CategoryDao categoryDao = connectionManager.categoryDao();
        price = findViewById(R.id.price);
        text = findViewById(R.id.offer);
        title = findViewById(R.id.title);
        save = findViewById(R.id.save_offer);
        AtomicReference<List<Category>> categories = new AtomicReference<>();
        //TODO

        new Thread(() -> {

            try {
                categories.set(categoryDao.readAllCategories());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                 offer[0] = offerDao.readOffer(offerIdId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                        ArrayList<String> catNames = (ArrayList<String>) categories.get().stream().map(Category::getCategory).collect(Collectors.toList());
                        setCategoryChips(catNames);
                        price.setText(String.valueOf(offer[0].getPrice()));
                        text.setText(offer[0].getText());
                        title.setText(offer[0].getTitle());
                    }
            );
        }).start();


        save.setOnClickListener(i -> new Thread(() -> {
            SharedPreferences preferences = this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String emailKey = preferences.getString("emailKey", "");
            final User[] user = new User[]{new User()};
            try {
                user[0] = userDao.readUser(emailKey);
            } catch (Exception e) {
                e.printStackTrace();
            }


            offer[0].setPrice(Long.parseLong(price.getText().toString()));
            offer[0].setText(text.getText().toString());
            offer[0].setTitle(title.getText().toString());
            offer[0].setRestaurant(user[0].getRestaurant());

            Optional<Category> category = categories.get().stream().filter(j -> j.getCategory().equals(this.category)).findFirst();
            offer[0].setCategory(category.get());
            try {
                offerDao.createOffer(offer[0]);
                Snackbar.make(profile, R.string.saved, Snackbar.LENGTH_SHORT)
                        .show();
                startActivity(new Intent(this, MenuPage.class));
            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.make(profile, R.string.error, Snackbar.LENGTH_SHORT)
                        .show();

            }
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

    public void setCategoryChips(ArrayList<String> categorys) {
        for (String category :
                categorys) {
            Chip mChip = (Chip) this.getLayoutInflater().inflate(R.layout.chip, null, false);
            mChip.setText(category);
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
            mChip.setPadding(paddingDp, 0, paddingDp, 0);
            mChip.setOnCheckedChangeListener((compoundButton, b) -> this.category = category);
            chipsPrograms.addView(mChip);
        }

    }
}
