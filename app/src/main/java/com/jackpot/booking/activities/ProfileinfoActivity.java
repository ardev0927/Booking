package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jackpot.booking.R;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.helper.PrefManager;
import com.jackpot.booking.models.User;
import com.squareup.picasso.Picasso;

public class ProfileinfoActivity extends AppCompatActivity {

    PrefManager pm;
    Gson gson;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileinfo);

        pm = new PrefManager(this);
        gson = new Gson();
        user = gson.fromJson(pm.getUser(), User.class);

        // Edit button click
        ImageView iv_edit = findViewById(R.id.iv_profile_edit);
        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EditProfile.class));
            }
        });

        // Set full name
        TextView tv_full_name = findViewById(R.id.tv_profile_name);
        tv_full_name.setText(user.getFull_name());

        // Set user id
        TextView tv_user_id = findViewById(R.id.tv_profile_userid);
        String user_id = String.format("User id: %04d", user.getUser_id());
        tv_user_id.setText(user_id);

        // Set email
        TextView tv_email = findViewById(R.id.tv_profile_email);
        tv_email.setText(user.getEmail());

        // Set avatar
        ImageView iv_avatar = findViewById(R.id.iv_profile_character);
        Picasso.with(this).load(G.DOMAIN_URL + user.getAvatar())
                .fit().centerCrop()
                .placeholder(R.drawable.group_21)
                .into(iv_avatar);
    }

    public void onBack(View view) {
        finish();
    }

    public void onLocation(View view) {

        startActivity(new Intent(getApplicationContext(), LocationActivity.class));
    }

    public void onFavourite(View view) {

    }

    public void onQA(View view) {
        startActivity(new Intent(getApplicationContext(), QueoneActivity.class));
    }

    public void onVouchers(View view) {

    }

    public void onSettings(View view) {

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