package com.jackpot.booking.models;

import java.util.List;

public class Shop {
    private int id;
    private String name;
    private String description;
    private String address;
    private float rating;
    private int rating_number;
    private String main_image;
    private String delivery;
    private List<Picture> pictures;
    private List<Categorie> categories;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public List<Picture> getPictures() {
        return pictures;
    }

    public List<Categorie> getCategories() {
        return categories;
    }
}
