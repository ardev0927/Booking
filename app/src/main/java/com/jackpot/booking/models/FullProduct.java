package com.jackpot.booking.models;

import java.util.List;

public class FullProduct extends Product{
    private List<Product> related_products;
    private String description;
    private String price;
    private List<ProductSize> sizes;
    private List<ProductColor> colors;

    public List<Product> getRelated_products() {
        return related_products;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public List<ProductSize> getSizes() {
        return sizes;
    }

    public List<ProductColor> getColors() {
        return colors;
    }
}
