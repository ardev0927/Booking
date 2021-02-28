package com.jackpot.booking.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.jackpot.booking.R;
import com.jackpot.booking.adapters.MainViewPagerAdapter;
import com.jackpot.booking.fragments.RestaurantsFragment;
import com.jackpot.booking.fragments.ShopsFragment;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.helper.PrefManager;
import com.jackpot.booking.models.User;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private NavigationView nav;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ImageView iv_toolbar;
    private TabLayout tl_main;
    private ViewPager vp_content;

    PrefManager pm;
    Gson gson;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);

        pm = new PrefManager(this);
        gson = new Gson();
        user = gson.fromJson(pm.getUser(), User.class);

        // Bottom
        LinearLayout ll_bottom = findViewById(R.id.ll_main_bottom);
        ll_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Navigation Drawer
        nav = findViewById(R.id.navigation_view);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_profile_info:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(), ProfileinfoActivity.class));
                        break;
                    case R.id.menu_booking:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(getApplicationContext(), MybookingActivity.class));
                        break;
                    case R.id.menu_voucher:
                        break;
                    case R.id.menu_setting:
                        break;
                    case R.id.menu_logout:
                        drawerLayout.closeDrawer(GravityCompat.START);

                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;
                }

                return false;
            }
        });

        drawerLayout = findViewById(R.id.drawer);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                // Set user information
                TextView tv_username = findViewById(R.id.tv_username);
                tv_username.setText(user.getFull_name());

                TextView tv_user_id = findViewById(R.id.tv_user_id);
                String user_id = String.format("User id: %04d", user.getUser_id());
                tv_user_id.setText(user_id);

                ImageView iv_avatar = findViewById(R.id.iv_user_photo);
                Picasso.with(MainActivity.this).load(G.DOMAIN_URL + user.getAvatar())
                        .fit().centerCrop()
                        .placeholder(R.drawable.group_2)
                        .into(iv_avatar);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
//        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


        // Tab layout
        tl_main = findViewById(R.id.main_tablayout);
        vp_content = findViewById(R.id.vp_content);

        getTabs();

    }

    private void getTabs() {
        final MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adapter.addFragment(RestaurantsFragment.newInstance(), "Restaurants");
                adapter.addFragment(ShopsFragment.newInstance(), "Shops");

                vp_content.setAdapter(adapter);
                tl_main.setupWithViewPager(vp_content);
            }
        });
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