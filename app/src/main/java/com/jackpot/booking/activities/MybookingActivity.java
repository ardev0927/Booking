package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.jackpot.booking.R;
import com.jackpot.booking.adapters.PreviousBookingAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MybookingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> titles, descs, counts, categories, prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);

        init();

        // Set date and time
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, yyyy\tHH:MM a");
        String cur_date_time = sdf.format(new Date());
        String[] date_time = cur_date_time.split("\t");

        TextView tv_date = findViewById(R.id.tv_booking_date);
        tv_date.setText(date_time[0]);

        TextView tv_time = findViewById(R.id.tv_booking_time);
        tv_time.setText(date_time[1]);

        recyclerView = findViewById(R.id.rv_prevbook);

        PreviousBookingAdapter pbAdapter = new PreviousBookingAdapter(this, titles, descs,
                counts, categories, prices);
        recyclerView.setAdapter(pbAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void init() {
        titles = new ArrayList<>();
        titles.add("Kellys Cafe and Espresso");
        titles.add("Panda Inn Mongolian Bar");
        titles.add("Juanito's Taqueria");
        titles.add("Kellys Cafe and Espresso");

        descs = new ArrayList<>();
        descs.add("882 Swift Courts Apt.918");
        descs.add("441 Bria Flat Apt.620");
        descs.add("478 Konopelski Union Apt. 506");
        descs.add("478 Konopelski Union Apt. 506");

        counts = new ArrayList<>();
        counts.add("01");
        counts.add("02");
        counts.add("03");
        counts.add("04");

        categories = new ArrayList<>();
        categories.add("Espresso");
        categories.add("Coffee");
        categories.add("Burger");
        categories.add("Espresso");

        prices = new ArrayList<>();
        prices.add("$9");
        prices.add("$22");
        prices.add("$30");
        prices.add("$100");
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