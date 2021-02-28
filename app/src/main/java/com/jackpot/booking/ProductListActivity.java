package com.jackpot.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jackpot.booking.activities.RestaurantCategories;
import com.jackpot.booking.activities.ShopCategories;
import com.jackpot.booking.adapters.MainCategoryAdapter;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView rv_main_categories;
    private MainCategoryAdapter mainCategoryAdapter;
    private List<Category> categories;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        categories = new ArrayList<>();

        // Get type
        int type = getIntent().getIntExtra("category", 0);
        this.type = type;

        requestApi(type);

        TextView tv_title = findViewById(R.id.tv_category_title);
        rv_main_categories = findViewById(R.id.rv_main_categories);

        if ( type == 1 )
            tv_title.setText("Restaurant");
        else
            tv_title.setText("Shop");

        showCategory();
    }

    private void requestApi(int type) {
        BookingApi bookingApi = G.retrofit.create(BookingApi.class);

        final Call<List<Category>> category_call;
        if( type == 1 ) {
            category_call = bookingApi.getResturantCategories();

        } else {
            category_call = bookingApi.getShopCategories();
        }
        category_call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.code() != 200) {
                    Toast.makeText(getApplicationContext(),
                            "Connection failed", Toast.LENGTH_SHORT).show();
                } else {
                    List<Category> temp = response.body();
                    if (temp != null) {
                        categories.addAll(temp);
                        //G.restCategories = categories;
                        //mainCategoryAdapter.notifyDataSetChanged();
                        mainCategoryAdapter.updateData(categories);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    private void showCategory() {
        mainCategoryAdapter = new MainCategoryAdapter(categories, 0);
        LinearLayoutManager horizontal_layout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_main_categories.setLayoutManager(horizontal_layout);
        rv_main_categories.setAdapter(mainCategoryAdapter);
    }

    public void searchCategory(View view) {
        Intent intent;
        if( type == 1 )
            intent = new Intent(this, RestaurantCategories.class);
        else
            intent = new Intent(this, ShopCategories.class);
        startActivity(intent);
    }
}