package com.jackpot.booking.models;

import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private String description;
    private String address;
    private float rating;
    private int rating_number;
    private String main_image;

    private List<Picture> pictures;
    private List<Categorie> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRating_number() {
        return rating_number;
    }

    public void setRating_number(int rating_number) {
        this.rating_number = rating_number;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPicture(List<Picture> picture) {
        this.pictures = picture;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategorie(List<Categorie> categorie) {
        this.categories = categorie;
    }
}
