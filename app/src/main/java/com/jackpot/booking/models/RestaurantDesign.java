package com.jackpot.booking.models;

import java.util.List;

public class RestaurantDesign {

    int restaurant_id;
    int x;
    int y;
    List<Seat> seats;

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
