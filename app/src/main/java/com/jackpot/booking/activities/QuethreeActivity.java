package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jackpot.booking.R;
import com.jackpot.booking.helper.PrefManager;

public class QuethreeActivity extends AppCompatActivity {

    private int que_state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quethree);

        TextView tv_que = findViewById(R.id.tv_que_ready);

        PrefManager pm = new PrefManager(this);
        que_state = pm.getQue();
        if ( que_state == 0 ) {
            tv_que.setText(R.string.number_ready);
        } else {
            tv_que.setText(R.string.table_ready);
        }
    }

    public void onBack(View view) {
        startActivity(new Intent(getApplicationContext(), QuetwoActivity.class));
        finish();
    }

    public void onApply(View view) {
        if ( que_state == 0 )
            startActivity(new Intent(getApplicationContext(), PaymentoneActivity.class));
        else
            startActivity(new Intent(getApplicationContext(), BookingoneActivity.class));
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