package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jackpot.booking.BookingApi;
import com.jackpot.booking.R;
import com.jackpot.booking.adapters.BookMenuAdapter;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.BookMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingBook extends AppCompatActivity {

    List<BookMenu> bookMenus;
    RecyclerView rv_menus;
    BookMenuAdapter bookMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_book);

        bookMenus = new ArrayList<>();

        String restaurant_name = getIntent().getStringExtra(G.REST_NAME);
        TextView tv_title = findViewById(R.id.tv_setting_menu_title);
        tv_title.setText(restaurant_name + "'s Menu");

        int restaurant_id = getIntent().getIntExtra(G.REST_ID, 0);
        BookingApi bookingApi = G.retrofit.create(BookingApi.class);
        Call<List<BookMenu>> bookMenusCall = bookingApi.getBookMenusData(restaurant_id);
        bookMenusCall.enqueue(new Callback<List<BookMenu>>() {
            @Override
            public void onResponse(Call<List<BookMenu>> call, Response<List<BookMenu>> response) {
                List<BookMenu> temp = response.body();
                if ( bookMenus != null ) {
                    bookMenus.addAll(temp);
                    bookMenuAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<BookMenu>> call, Throwable t) {
                int kkk = 0;
            }
        });
        //
        rv_menus = findViewById(R.id.rv_setting_menu);
        bookMenuAdapter = new BookMenuAdapter(bookMenus);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_menus.setLayoutManager(linearLayoutManager);
        rv_menus.setAdapter(bookMenuAdapter);

        // go to booking
        Button btn_booking = findViewById(R.id.btn_setting_book);
        btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingBook.this, BookingoneActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        bookMenus = new ArrayList<>(4);

        BookMenu bookMenu1 = new BookMenu();
        bookMenu1.setId(3);
        bookMenu1.setTitle("Coffee");
        bookMenu1.setPrice("$5");

        BookMenu bookMenu2 = new BookMenu();
        bookMenu2.setId(4);
        bookMenu2.setTitle("Tea");
        bookMenu2.setPrice("$5");

        BookMenu bookMenu3 = new BookMenu();
        bookMenu3.setId(7);
        bookMenu3.setTitle("Hamburger");
        bookMenu3.setPrice("$3");

        BookMenu bookMenu4 = new BookMenu();
        bookMenu4.setId(8);
        bookMenu4.setTitle("Cake");
        bookMenu4.setPrice("$10");

        bookMenus.add(bookMenu1);
        bookMenus.add(bookMenu2);
        bookMenus.add(bookMenu3);
        bookMenus.add(bookMenu4);
    }

    public void onBack(View view) {
        finish();
    }
}