package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jackpot.booking.BookingApi;
import com.jackpot.booking.MainLandingActivity;
import com.jackpot.booking.R;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.helper.PrefManager;
import com.jackpot.booking.models.Login;
import com.jackpot.booking.models.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email, et_pwd;
    private CheckBox remember;
    private boolean is_remember;
    private ArrayList<String> arrayList;
    PrefManager pm;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init
        gson = new Gson();
        pm = new PrefManager(this);

        //
        arrayList = new ArrayList<>();

        et_email = findViewById(R.id.et_login_email);
        et_pwd = findViewById(R.id.et_login_pw);
        remember = findViewById(R.id.cb_remember);

        // do login
        is_remember = pm.getRemember();
        if ( is_remember ) {
            String json = pm.getLoginInfo();
            if (json.isEmpty()) {
                Toast.makeText(getApplicationContext(),"There is something error",Toast.LENGTH_SHORT).show();
            } else {
                Type type = new TypeToken<List<String>>() {
                }.getType();
                List<String> arry = gson.fromJson(json, type);
                et_email.setText(arry.get(0));
                et_pwd.setText(arry.get(1));
            }
            remember.setChecked(is_remember);
        }

        // remember
        remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_remember = !is_remember;
                pm.setRemember(is_remember);
            }
        });

        // start forget activity
        TextView tv_fg_pw = findViewById(R.id.tv_forgetpw);
        tv_fg_pw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgetpasswordActivity.class));
                finish();
            }
        });

        // back button event
        ImageView iv_back = findViewById(R.id.iv_login_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainmenuActivity.class));
                finish();
            }
        });

        // get started
        ImageView iv_gs = findViewById(R.id.iv_login_gs);
        iv_gs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = et_email.getText().toString();
                String pw = et_pwd.getText().toString();
                if ( em.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Please input your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( pw.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Please input your password", Toast.LENGTH_SHORT).show();
                    return;
                }


                doLogin(em, pw);
            }
        });
    }

    private void setRemember() {
        if ( is_remember ) {
            String email = et_email.getText().toString().trim();
            String pwd = et_pwd.getText().toString().trim();
            arrayList.add(email);
            arrayList.add(pwd);

            Gson gson = new Gson();
            String json = gson.toJson(arrayList);

            pm.setLoginInfo(json);
        }
    }

    private void doLogin(String email, String password) {

        User user = new User(email, password);
        BookingApi bookingApi = G.retrofit.create(BookingApi.class);
        Call<User> loginCall = bookingApi.loginUser(user);
        loginCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // save user information to preference
                setRemember();

                if ( response.code() == 200 ) {
                    User user = response.body();
                    if ( user != null ) {
                        // go to main screen
                        String user_json = gson.toJson(user);
                        pm.setUser(user_json);
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        startActivity(new Intent(getApplicationContext(), MainLandingActivity.class));
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                int kkk = 0;
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