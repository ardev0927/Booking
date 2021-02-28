package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jackpot.booking.R;
import com.jackpot.booking.adapters.MostPopularAdapter;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.helper.PrefManager;
import com.jackpot.booking.models.Popular;

import java.util.ArrayList;

public class ShowAllPopularActivity extends AppCompatActivity {

    RecyclerView rv_all_populars;
    ArrayList<Popular> all_populars;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_popular);

        //
        TextView textView = findViewById(R.id.tv_sap_title);
        int type = getIntent().getIntExtra(PrefManager.KEY_METHOD, 0);

        MostPopularAdapter adapter = null;
        if ( type == 0 ) {
            textView.setText("Restaurants");

             adapter = new MostPopularAdapter(this, G.rest_populars, type);
        }
        else {
            textView.setText("Shops");

            adapter = new MostPopularAdapter(this, G.shop_populars, type);
        }

        //
        rv_all_populars = findViewById(R.id.rv_all_popular);

        LinearLayoutManager vertical_layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_all_populars.setLayoutManager(vertical_layout);
        rv_all_populars.setAdapter(adapter);

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