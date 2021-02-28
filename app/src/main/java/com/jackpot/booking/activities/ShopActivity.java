package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackpot.booking.BookingApi;
import com.jackpot.booking.R;
import com.jackpot.booking.adapters.CartShopItemAdapter;
import com.jackpot.booking.adapters.ShopCategoryAdapter;
import com.jackpot.booking.adapters.ShopProductAdapter;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Categorie;
import com.jackpot.booking.models.Picture;
import com.jackpot.booking.models.Product;
import com.jackpot.booking.models.Shop;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {

    RecyclerView rv_category, rv_product1, rv_product2;

    ShopCategoryAdapter categoryAdapter;
    ShopProductAdapter productAdapter;

    Shop shop;
    List<Categorie> categories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        int id = getIntent().getIntExtra(G.SHOP_ID, 0);
        BookingApi bookingApi = G.retrofit.create(BookingApi.class);
        Call<Shop> shop_call = bookingApi.getShopData(id);
        shop_call.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                shop = response.body();
                if ( shop != null ) {
                    categories = shop.getCategories();

                    // Show main
                    showMain();
                    shopProductAndSubmenu(categories);
                    showImage();
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {

            }
        });

    }

    private void shopProductAndSubmenu(List<Categorie> categories) {
        // show menu and sub menu
        rv_product2 = findViewById(R.id.rv_product_2);
        ArrayList<Product> temp = new ArrayList<>();
        temp.addAll(categories.get(0).getProducts());
        productAdapter = new ShopProductAdapter(this, temp);
        LinearLayoutManager prod_two_manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_product2.setLayoutManager(prod_two_manager);
        rv_product2.setAdapter(productAdapter);

        rv_product1 = findViewById(R.id.rv_product_1);
        categoryAdapter = new ShopCategoryAdapter(this, categories, productAdapter);
        LinearLayoutManager prod_one_manager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_product1.setLayoutManager(prod_one_manager);
        rv_product1.setAdapter(categoryAdapter);

    }

    private void showImage() {
        List<Product> allProducts = new ArrayList<>();

        for ( Categorie categorie: categories ) {
            List<Product> subProducts = categorie.getProducts();
            allProducts.addAll(subProducts);
        }

        rv_category = findViewById(R.id.rv_shop_category);
        CartShopItemAdapter adapter = new CartShopItemAdapter(this, allProducts);
        LinearLayoutManager horizontal_layout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_category.setLayoutManager(horizontal_layout);
        rv_category.setAdapter(adapter);
    }

    private void showMain() {
        // set main image
        ImageView iv_main = findViewById(R.id.iv_shop_main);
        Picasso.with(this).load(G.DOMAIN_URL + shop.getMain_image())
                .fit().centerCrop()
//                .placeholder(R.drawable.rest_rect_7)
                .into(iv_main);

        // set title
        TextView tv_title = findViewById(R.id.tv_shop_title);
        tv_title.setText(shop.getName());

        // set description
        TextView tv_description = findViewById(R.id.tv_shop_description);
        tv_description.setText(shop.getDescription());

        // set address
        TextView tv_address = findViewById(R.id.tv_shop_address);
        tv_address.setText(shop.getAddress());

        // set rating
        TextView tv_rating = findViewById(R.id.tv_shop_rating);
        tv_rating.setText(String.valueOf(shop.getRating()));

        // set rating number
        TextView tv_rating_number = findViewById(R.id.tv_shop_rating_number);
        tv_rating_number.setText(String.format("(%d ratings)", shop.getRating_number()));
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