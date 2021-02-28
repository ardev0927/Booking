package com.jackpot.booking.models;

import java.util.List;

public class Categorie {
    private int id;
    private String name;
    private String image;
    private List<Menu> menus;
    private List<Product> products;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Menu> getMenu() {
        return menus;
    }

    public List<Product> getProducts() {
        return products;
    }
}
