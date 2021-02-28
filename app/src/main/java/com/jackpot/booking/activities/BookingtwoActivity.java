package com.jackpot.booking.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jackpot.booking.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookingtwoActivity extends AppCompatActivity implements View.OnClickListener {

    private int mYear, mMonth, mDay;
    private Calendar calendar;
    private TextView tv_reservation_date, tv_amount_number;
    private ImageView iv_plus, iv_minus;
    private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    int mAmount, initAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingtwo);

        // show today date
        calendar = Calendar.getInstance();
        tv_reservation_date = findViewById(R.id.tv_reservation_date);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM, dd");
        String today = sdf.format(calendar.getTime());
        tv_reservation_date.setText(today);

        // amount
        initAmount = mAmount = 4;
        tv_amount_number = findViewById(R.id.tv_amount_number);
        tv_amount_number.setText(String.valueOf(mAmount));

        // plus, minus
        iv_plus = findViewById(R.id.iv_amount_plus);
        iv_plus.setOnClickListener(this);

        iv_minus = findViewById(R.id.iv_amount_minus);
        iv_minus.setOnClickListener(this);

        // show calendar when click button
        LinearLayout ll_calendar = findViewById(R.id.ll_booking_calendar);
        ll_calendar.setOnClickListener(this);
    }

    public void onBack(View view) {
        startActivity(new Intent(getApplicationContext(), BookingoneActivity.class));
        finish();
    }

    public void onBookTable(View view) {
        startActivity(new Intent(getApplicationContext(), PaymentoneActivity.class));
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_booking_calendar:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DATE);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String mon = months[month];
                        String date = mon + String.format(", %d", dayOfMonth);
                        tv_reservation_date.setText(date);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.iv_amount_plus:
                if ( initAmount > mAmount ) {
                    mAmount += 1;
                    tv_amount_number.setText(String.valueOf(mAmount));
                }
                break;
            case R.id.iv_amount_minus:
                if ( mAmount >= 0 ) {
                    mAmount -= 1;
                    tv_amount_number.setText(String.valueOf(mAmount));
                }
                break;
        }
    }
}