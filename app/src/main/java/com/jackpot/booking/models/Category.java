package com.jackpot.booking.models;

public class Category {

    private int id;
    private String image;
    private String name;
    private int numbers;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumbers() {
        return numbers;
    }
}
