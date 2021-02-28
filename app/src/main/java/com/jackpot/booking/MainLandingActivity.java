package com.jackpot.booking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainLandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);

    }

    public void gotoRestaurant(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra("category", 1);
        startActivity(intent);
    }

    public void gotoShop(View view) {
        Intent intent = new Intent(this, ProductListActivity.class);
        intent.putExtra("category", 2);
        startActivity(intent);
    }

}