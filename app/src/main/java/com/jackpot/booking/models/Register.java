package com.jackpot.booking.models;

import com.google.gson.annotations.SerializedName;

public class Register {
    String full_name;
    String email;
    String password;

    public Register(String fullname, String email, String password) {
        this.full_name = fullname;
        this.email = email;
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
