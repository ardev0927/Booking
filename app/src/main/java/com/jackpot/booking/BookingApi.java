package com.jackpot.booking;

import com.jackpot.booking.models.BookMenu;
import com.jackpot.booking.models.Category;
import com.jackpot.booking.models.FullProduct;
import com.jackpot.booking.models.Menu;
import com.jackpot.booking.models.Popular;
import com.jackpot.booking.models.Register;
import com.jackpot.booking.models.Restaurant;
import com.jackpot.booking.models.RestaurantMenu;
import com.jackpot.booking.models.RestuatrantImage;
import com.jackpot.booking.models.Shop;
import com.jackpot.booking.models.ShopImage;
import com.jackpot.booking.models.ShopMenu;
import com.jackpot.booking.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookingApi {

    @POST("api/v1/auth/login/")
    Call<User> loginUser(@Body User login);

    @POST("api/v1/auth/register/")
    Call<Register> registerUser(@Body Register register);

    @GET("api/restaurants/most-popular/")
    Call<List<Popular>> getRestuarantData();
    @GET("api/shops/most-popular/")
    Call<List<Popular>> getShopData();

    @GET("api/restaurants/{id}/")
    Call<Restaurant> getRestuarantData(@Path("id") int id);
    @GET("api/shops/{id}/")
    Call<Shop> getShopData(@Path("id") int id);

    @GET("api/category/{id}/restaurants/")
    Call<List<Popular>> getRestaurantCategoryData(@Path("id") int id);
    @GET("api/category/{id}/shops/")
    Call<List<Popular>> getShopCategoryData(@Path("id") int id);

    @GET("api/products/{id}/")
    Call<FullProduct> getFullProductData(@Path("id") int id);

    @GET("api/restaurants/{id}/menus/")
    Call<List<BookMenu>> getBookMenusData(@Path("id") int id);

    @GET("api/menus/{id}")
    Call<Menu> getMenu(@Path("id") int id);

    @GET("api/restaurants/all-categories/")
    Call<List<Category>> getResturantCategories();
    @GET("api/shops/all-categories/")
    Call<List<Category>> getShopCategories();

    @GET("api/res/{id}")
    Call<Popular> getRestIdData(@Path("id") int id);

    @GET("api/shop/{id}")
    Call<Popular> getShopIdData(@Path("id") int id);

    @GET("api/resmenu")
    Call<List<RestaurantMenu>> getRestaurantMenu();
    @GET("api/resimage")
    Call<List<RestuatrantImage>> getRestaurantImage();

    @GET("api/shopimage")
    Call<List<ShopImage>> getShopImage();
    @GET("api/shopproduct")
    Call<List<ShopMenu>> getShopMenu();

}
