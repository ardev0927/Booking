package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jackpot.booking.R;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private String fullName;
    private String emailAddress;
    private String password;
    private String confirmPassword;

    private EditText fn, email, pw, cpw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fn = findViewById(R.id.et_signup_fullname);
        email = findViewById(R.id.et_signup_email);
        pw = findViewById(R.id.et_signup_pw);
        cpw = findViewById(R.id.et_signup_cpw);


        // back button event
        ImageView iv_back = findViewById(R.id.iv_signup_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainmenuActivity.class));
                finish();
            }
        });

        // signup button event
        ImageView iv_signup_gs = findViewById(R.id.iv_signup_gs);
        iv_signup_gs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verified()) {
                    doRegister(fn.getText().toString(), email.getText().toString(),
                            pw.getText().toString());
//                    startActivity(new Intent(getApplicationContext(), VerificationActivity.class));
//                    finish();
                }
            }
        });

    }

    private boolean verified() {
        fullName = fn.getText().toString();
        if ( fullName.isEmpty() ) {
            Toast.makeText(getApplicationContext(), "Please input your full name", Toast.LENGTH_SHORT).show();
            return false;
        }
        emailAddress = email.getText().toString();
        if ( emailAddress.isEmpty() ) {
            Toast.makeText(getApplicationContext(), "Please input your email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        password = pw.getText().toString();
        if ( password.isEmpty() ) {
            Toast.makeText(getApplicationContext(), "Please input password", Toast.LENGTH_SHORT).show();
            return false;
        }
        confirmPassword = cpw.getText().toString();
        if ( confirmPassword.isEmpty() ) {
            Toast.makeText(getApplicationContext(), "Please input confirm password", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(
                    getApplicationContext(),
                    "Password and confirm password does not match",
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void doRegister(String fullname, String email, String password) {
        Register register = new Register(fullname, email, password);
        Call<Register> registerCall = G.bookingApi_1.registerUser(register);
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                Register register1 = response.body();
                if ( register1 != null ) {
//                    startActivity(new Intent(getApplicationContext(), VerificationActivity.class));
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

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