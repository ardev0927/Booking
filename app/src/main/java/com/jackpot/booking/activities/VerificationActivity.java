package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jackpot.booking.R;

import java.util.Objects;

public class VerificationActivity extends AppCompatActivity {
    private String s1, s2, s3, s4, ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        EditText number_one = findViewById(R.id.etn_group1686_1);
        final EditText number_two = findViewById(R.id.etn_group1686_2);
        final EditText number_three = findViewById(R.id.etn_group1686_3);
        final EditText number_four = findViewById(R.id.etn_group1686_4);

        if( number_one.requestFocus() ) {
            showKeyboard(number_one);
        }

        number_one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                s1 = s.toString();
                number_two.requestFocus();
            }
        });

        number_two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                s2 = s.toString();
                number_three.requestFocus();
            }
        });

        number_three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                s3 = s.toString();
                number_four.requestFocus();
            }
        });

        number_four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                s4 = s.toString();
                ss = s1+s2+s3+s4;
                hideKeyboard(Objects.requireNonNull(getCurrentFocus()));
                sendDigitalCode();
            }
        });

        ImageView iv_back = findViewById(R.id.iv_veri_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                finish();
            }
        });

        TextView resend = findViewById(R.id.tv_resend_now);
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDigitalCode();
            }
        });
    }

    private void sendDigitalCode() {
        if ( !ss.isEmpty() ) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
    }

    private void showKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
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