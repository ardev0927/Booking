package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jackpot.booking.MainLandingActivity;
import com.jackpot.booking.R;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.helper.PrefManager;
import com.jackpot.booking.models.Login;
import com.jackpot.booking.models.User;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private final int MILLIS_IN_FUTURE = 3000;
    private final int COUNTDOWN_INTERVAL = 1000;

    PrefManager pm;
    boolean is_login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        hideSystemUI();

        CountDownTimer timer = new CountDownTimer(MILLIS_IN_FUTURE, COUNTDOWN_INTERVAL) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                pm = new PrefManager(SplashActivity.this);
                boolean is_skip = pm.getFirst();

                if ( !is_skip ) {
                    startActivity(new Intent(getApplicationContext(), SkipActivity.class));
                    finish();
                }
                else {
                    String userInfo = pm.getLoginInfo();
                    if ( !userInfo.isEmpty() )
                        doLogin(userInfo);
                    else {
                        startActivity(new Intent(getApplicationContext(), MainmenuActivity.class));
                        finish();
                    }
                }
            }
        };
        timer.start();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
//            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private void doLogin(String userInfo) {
        Gson gson = new Gson();

        Type type = new TypeToken<List<String>>() {}.getType();
        List<String> arry = gson.fromJson(userInfo, type);
        User user = new User(arry.get(0), arry.get(1));
        Call<User> loginCall = G.bookingApi.loginUser(user);
        loginCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if ( response.code() == 200 ) {
                    User user = response.body();
                    if( user != null ) {
                        String user_json = gson.toJson(user);
                        pm.setUser(user_json);

                        startActivity(new Intent(getApplicationContext(), MainLandingActivity.class));
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), MainmenuActivity.class));
                }
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                int kkk = 0;
            }
        });
    }
}