package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jackpot.booking.BookingApi;
import com.jackpot.booking.R;
import com.jackpot.booking.adapters.MostPopularAdapter;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Popular;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopCategories extends AppCompatActivity {

    List<Popular> categories;
    RecyclerView rv_rest_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_categories);

        // Get category
        String category = getIntent().getStringExtra(G.SHOP_CAT);
        TextView iv_title = findViewById(R.id.tv_title_shop_categories);
        iv_title.setText(category);

        BookingApi bookingApi = G.retrofit.create(BookingApi.class);

        int id = getIntent().getIntExtra(G.SHOP_CAT_ID, 0);
        Call<List<Popular>> rest_cat_call = bookingApi.getShopData();
        rest_cat_call.enqueue(new Callback<List<Popular>>() {
            @Override
            public void onResponse(Call<List<Popular>> call, Response<List<Popular>> response) {
                categories = response.body();
                if ( categories != null ) {
                    showShops(categories);
                }
            }

            @Override
            public void onFailure(Call<List<Popular>> call, Throwable t) {

            }
        });

    }

    private void showShops(List<Popular> categories) {
        //
        rv_rest_menu = findViewById(R.id.rv_shop_menu);
        MostPopularAdapter adapter = new MostPopularAdapter(this, categories, 1);
        LinearLayoutManager vertical_layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_rest_menu.setLayoutManager(vertical_layout);
        rv_rest_menu.setAdapter(adapter);
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