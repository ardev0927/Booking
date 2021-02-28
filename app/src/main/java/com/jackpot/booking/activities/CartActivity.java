package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackpot.booking.BookingApi;
import com.jackpot.booking.R;
import com.jackpot.booking.adapters.ProductColorAdapter;
import com.jackpot.booking.adapters.ProductSizeAdapter;
import com.jackpot.booking.adapters.RelativeProductAdapter;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.helper.PrefManager;
import com.jackpot.booking.models.FullProduct;
import com.jackpot.booking.models.Product;
import com.jackpot.booking.models.ProductColor;
import com.jackpot.booking.models.ProductSize;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements RelativeProductAdapter.ListItemClickListener{

    RecyclerView rv_cart_relative, rv_cart_size, rv_product_color;
    List<Product> relative_products;
    List<ProductSize> productSizes;
    List<ProductColor> productColors;
    RelativeProductAdapter relative_adapter;
    ProductSizeAdapter productSizeAdapter;
    ProductColorAdapter productColorAdapter;
    FullProduct fullProduct;
    ImageView iv_main;
    TextView tv_item_title, tv_desc, tv_price, tv_cart_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        relative_products = new ArrayList<>();
        productSizes = new ArrayList<>();
        productColors = new ArrayList<>();

        iv_main = findViewById(R.id.iv_item_main);
        tv_item_title = findViewById(R.id.tv_item_title);
        tv_desc = findViewById(R.id.tv_item_description);
        tv_price = findViewById(R.id.tv_item_price);
        rv_cart_relative = findViewById(R.id.rv_relative_product);
        rv_cart_size = findViewById(R.id.rv_cart_size);
        rv_product_color = findViewById(R.id.rv_cart_color);

        tv_cart_title = findViewById(R.id.tv_cart_title);

        int id = getIntent().getIntExtra(G.PRODUCT_ID, 0);
        String title = getIntent().getStringExtra(G.PRODUCT_NAME);

        tv_cart_title.setText(title);

        callApiFunction(id);

        showRelativeProducts();
        showProductSize();
        showProductColor();
    }

    private void callApiFunction(int id) {
        // Invisible items
        setVisibleItems(View.INVISIBLE);

        BookingApi bookingApi = G.retrofit.create(BookingApi.class);
        Call<FullProduct> fullProductCall = bookingApi.getFullProductData(id);
        fullProductCall.enqueue(new Callback<FullProduct>() {
            @Override
            public void onResponse(Call<FullProduct> call, Response<FullProduct> response) {
                fullProduct = response.body();
                if( fullProduct != null ) {
                    // Visible items
                    setVisibleItems(View.VISIBLE);

                    // show items
                    showFullData(fullProduct);

                    // relative products
                    relative_products.addAll(fullProduct.getRelated_products());
                    relative_adapter.notifyDataSetChanged();
                    // product size
                    productSizes.addAll(fullProduct.getSizes());
                    productSizeAdapter.notifyDataSetChanged();
                    // product color
                    productColors.addAll(fullProduct.getColors());
                    productColorAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<FullProduct> call, Throwable t) {
                int kkk = 0;
            }
        });
    }

    private void setVisibleItems(int state) {
        iv_main.setVisibility(state);
        tv_item_title.setVisibility(state);
        tv_desc.setVisibility(state);
        tv_price.setVisibility(state);
        rv_cart_relative.setVisibility(state);
    }

    private void showRelativeProducts() {
        relative_adapter = new RelativeProductAdapter(this, relative_products);
        LinearLayoutManager horizontal_layout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_cart_relative.setLayoutManager(horizontal_layout);
        rv_cart_relative.setAdapter(relative_adapter);
    }

    private void showProductSize() {
        productSizeAdapter = new ProductSizeAdapter(productSizes);
        LinearLayoutManager horizontal_layout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_cart_size.setLayoutManager(horizontal_layout);
        rv_cart_size.setAdapter(productSizeAdapter);
    }

    private void showProductColor() {
        productColorAdapter = new ProductColorAdapter(productColors);
        LinearLayoutManager horizontal_layout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rv_product_color.setLayoutManager(horizontal_layout);
        rv_product_color.setAdapter(productColorAdapter);
    }

    private void showFullData( FullProduct fullProduct ) {
        // set main image
        Picasso.with(this).load(fullProduct.getPicture())
                .fit().centerCrop()
                .into(iv_main);

        // set title
        tv_item_title.setText(fullProduct.getTitle());

        // set description
        tv_desc.setText(fullProduct.getDescription());

        // set item price
        tv_price.setText(fullProduct.getPrice());

    }

    public void onBack(View view) {
        finish();
    }

    public void onAddBucket(View view) {
        PrefManager pm = new PrefManager(this);
        pm.setQue(0); // number

        startActivity(new Intent(getApplicationContext(), QueoneActivity.class));
    }

    @Override
    public void onListItemClick(int position) {
        int id = relative_products.get(position).getId();
        callApiFunction(id);
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