package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackpot.booking.BookingApi;
import com.jackpot.booking.R;
import com.jackpot.booking.adapters.CartItemAdapter;
import com.jackpot.booking.adapters.RestaurantCategoryAdapter;
import com.jackpot.booking.adapters.RestaurantMenuAdapter;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.helper.PrefManager;
import com.jackpot.booking.models.Categorie;
import com.jackpot.booking.models.Menu;
import com.jackpot.booking.models.Picture;
import com.jackpot.booking.models.Product;
import com.jackpot.booking.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestuarantActivity extends AppCompatActivity {

    RecyclerView rv_category, rv_product1, rv_product2;

    Restaurant restaurant;
    List<Picture> pictures;
    List<Categorie> categories;
    List<Menu> allMenus;

    RestaurantCategoryAdapter categoryAdapter;
    RestaurantMenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restuarant);

        allMenus = new ArrayList<>();

        int id = getIntent().getIntExtra(G.REST_ID, 0);
        BookingApi bookingApi = G.retrofit.create(BookingApi.class);
        Call<Restaurant> res_call = bookingApi.getRestuarantData(id);
        res_call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                restaurant = response.body();
                if( restaurant != null ) {

                    pictures = restaurant.getPictures();
                    categories = restaurant.getCategories();

                    // show main
                    showMain();
                    showRestaurantImage(pictures);
                    showPicturesAndCategories(categories);
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

            }
        });

        //
        ImageView iv_open_live = findViewById(R.id.iv_restaurant_open);
        iv_open_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LiveActivity.class));
            }
        });

        // add book
        Button btn_add_book = findViewById(R.id.btn_restaurant_add_book);
        btn_add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RestuarantActivity.this, SettingBook.class);
                intent.putExtra(G.REST_NAME, restaurant.getName());
                intent.putExtra(G.REST_ID, restaurant.getId());
                startActivity(intent);
            }
        });

    }

    private void showMain() {
        // set main image
        ImageView iv_main = findViewById(R.id.iv_resturant_main);
        Picasso.with(this).load(G.DOMAIN_URL + restaurant.getMain_image())
                .fit().centerCrop()
//                .placeholder(R.drawable.rest_rect_7)
                .into(iv_main);

        // set title
        TextView tv_title = findViewById(R.id.tv_restuarant_title);
        tv_title.setText(restaurant.getName());

        // set description
        TextView tv_description = findViewById(R.id.tv_restuarant_description);
        tv_description.setText(restaurant.getDescription());

        // set address
        TextView tv_address = findViewById(R.id.tv_restuarant_address);
        tv_address.setText(restaurant.getAddress());

        // set rating
        TextView tv_rating = findViewById(R.id.tv_restuarant_rating_number);
        tv_rating.setText(String.valueOf(restaurant.getRating()));

        // set rating number
        TextView tv_rating_number = findViewById(R.id.tv_restuarant_rating_number);
        tv_rating_number.setText(String.format("(%d ratings)", restaurant.getRating_number()));
    }

    private void showPicturesAndCategories(List<Categorie> categories) {
        //
        rv_product2 = findViewById(R.id.rv_rest_product_2);

        ArrayList<Menu> temp = new ArrayList<>();
        temp.addAll(categories.get(0).getMenu());

        menuAdapter = new RestaurantMenuAdapter(this, temp);
        LinearLayoutManager prod_two_manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_product2.setLayoutManager(prod_two_manager);
        rv_product2.setAdapter(menuAdapter);

        rv_product1 = findViewById(R.id.rv_rest_product_1);
        categoryAdapter = new RestaurantCategoryAdapter(this, categories, menuAdapter);
        LinearLayoutManager prod_one_manager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_product1.setLayoutManager(prod_one_manager);
        rv_product1.setAdapter(categoryAdapter);

    }

    private void showRestaurantImage(List<Picture> pictures) {
        for ( Categorie categorie: categories ) {
            List<Menu> subMenus = categorie.getMenu();
            allMenus.addAll(subMenus);
        }


        rv_category = findViewById(R.id.rv_rest_category);
        CartItemAdapter adapter = new CartItemAdapter(this, allMenus, 0);
        LinearLayoutManager horizontal_layout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_category.setLayoutManager(horizontal_layout);
        rv_category.setAdapter(adapter);
    }

    public void onBack(View view) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}