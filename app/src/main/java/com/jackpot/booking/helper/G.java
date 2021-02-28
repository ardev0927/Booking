package com.jackpot.booking.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.jackpot.booking.BookingApi;
import com.jackpot.booking.models.Category;
import com.jackpot.booking.models.Popular;
import com.jackpot.booking.models.Restaurant;
import com.jackpot.booking.models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.ContentValues.TAG;

public class G {
    public static String DOMAIN_URL = "http://13.58.220.233:88";
    public static String BASE_URL = "http://13.58.220.233:88/";
    public static String BASE_URL_1 = "http://13.58.220.233/";

    public static final String RESAPI_KEY = "RestuarantApi";
    public static final String ITEMAPI_KEY = "ItemApi";

    public static final String REST_CAT = "RestCategory";
    public static final String SHOP_CAT = "ShopCategory";

    public static final String REST_CAT_ID = "RestCategoryID";
    public static final String SHOP_CAT_ID = "ShopCategoryID";

    public static final String EXP_CAT = "ExplorerCategory";

    public static final String REST_ID = "restaurant_id";
    public static final String REST_NAME = "restaurant_name";
    public static final String SHOP_ID = "restaurant_id";

    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";


    public static ArrayList<Popular> rest_populars;
    public static ArrayList<Popular> shop_populars;

    public static List<Category> restCategories;
    public static List<Category> shopCategories;

    // Retrofit Builder
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit retrofit_1 = new Retrofit.Builder()
            .baseUrl(BASE_URL_1)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    public static BookingApi bookingApi = retrofit.create(BookingApi.class);
    public static BookingApi bookingApi_1 = G.retrofit_1.create(BookingApi.class);

    public static ArrayList<ArrayList<String>> restSubMenus = new ArrayList<>();
    public static ArrayList<ArrayList<String>> shopSubMenus = new ArrayList<>();

}
