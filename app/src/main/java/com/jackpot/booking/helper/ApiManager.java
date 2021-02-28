package com.jackpot.booking.helper;

import com.jackpot.booking.BookingApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static BookingApi bookingApi;
    private static ApiManager apiManager;

    private ApiManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(G.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bookingApi = retrofit.create(BookingApi.class);
    }

    public static ApiManager getInstance() {
        if ( apiManager == null )
            apiManager = new ApiManager();
        return apiManager;
    }

}
