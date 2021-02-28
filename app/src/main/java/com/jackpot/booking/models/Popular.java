package com.jackpot.booking.models;

public class Popular {

    private int id;
    private String name;
    private String delivery;
    private String address;
    private float rating;
    private int rating_number;
    private String main_image;

    public Popular() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public float getRating() {
        return rating;
    }

    public int getRating_number() {
        return rating_number;
    }

    public String getMain_image() {
        return main_image;
    }

    public String getDelivery() {
        return delivery;
    }
}
