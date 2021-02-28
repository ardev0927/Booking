package com.jackpot.booking.models;

public class BookTable {
    int restaurant_id;
    int x, y;
    int amount;
    String reservation_date;
    String available_time;

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAmount() {
        return amount;
    }

    public String getReservation_date() {
        return reservation_date;
    }

    public String getAvailable_time() {
        return available_time;
    }
}
