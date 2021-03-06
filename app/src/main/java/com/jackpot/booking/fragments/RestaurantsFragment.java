package com.jackpot.booking.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jackpot.booking.BookingApi;
import com.jackpot.booking.R;
import com.jackpot.booking.activities.ShowAllCategories;
import com.jackpot.booking.activities.ShowAllPopularActivity;
import com.jackpot.booking.adapters.MainCategoryAdapter;
import com.jackpot.booking.adapters.MostPopularAdapter;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.helper.PrefManager;
import com.jackpot.booking.models.Category;
import com.jackpot.booking.models.Popular;
import com.jackpot.booking.models.Restaurant;
import com.jackpot.booking.models.RestaurantMenu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RestaurantsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rv_main_categories, rv_most_popular;

    private List<Category> categories;
    private List<Popular> populars;

    private MainCategoryAdapter mainCategoryAdapter;
    private MostPopularAdapter mostPopularAdapter;

    private View view;


    public RestaurantsFragment() {
        categories = new ArrayList<>();
        populars = new ArrayList<>();

        BookingApi bookingApi = G.retrofit.create(BookingApi.class);

        Call<List<Category>> category_call = bookingApi.getResturantCategories();
        category_call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.code() != 200) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Connection failed", Toast.LENGTH_SHORT).show();
                } else {
                    List<Category> temp = response.body();
                    if (temp != null) {
                        categories.addAll(temp);
                        G.restCategories = categories;
                        mainCategoryAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });

        Call<List<Popular>> popular_call = bookingApi.getRestuarantData();
        popular_call.enqueue(new Callback<List<Popular>>() {
            @Override
            public void onResponse(Call<List<Popular>> call, Response<List<Popular>> response) {
                if (response.code() != 200) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Connection failed", Toast.LENGTH_SHORT).show();
                } else {
                    List<Popular> temp = response.body();

                    assert temp != null;

                    populars.addAll(temp);

                    assert mostPopularAdapter != null;
                    mostPopularAdapter.notifyDataSetChanged();

                    G.rest_populars = new ArrayList<>(populars);
                }
            }

            @Override
            public void onFailure(Call<List<Popular>> call, Throwable t) {
                int kkk = 0;
            }
        });

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RestaurantsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestaurantsFragment newInstance(String param1, String param2) {
        RestaurantsFragment fragment = new RestaurantsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static RestaurantsFragment newInstance() {
        RestaurantsFragment fragment = new RestaurantsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);
        this.view = view;

        rv_most_popular = view.findViewById(R.id.rv_main_popular);
        rv_main_categories = view.findViewById(R.id.rv_main_categories);

        showCategory();
        showRestaurant();

        // all popular
        LinearLayout linearLayout = view.findViewById(R.id.ll_all_popular);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllPopularActivity.class);
                intent.putExtra(PrefManager.KEY_METHOD, 0);
                startActivity(intent);
            }
        });

        // explorer categories
        LinearLayout ll_explorer = view.findViewById(R.id.ll_rst_categories);
        ll_explorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowAllCategories.class);
                intent.putExtra(G.EXP_CAT, 0);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void showRestaurant() {
        mostPopularAdapter = new MostPopularAdapter(view.getContext(), populars, 0);
        LinearLayoutManager vertical_layout = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.VERTICAL, false);
        rv_most_popular.setLayoutManager(vertical_layout);
        rv_most_popular.setAdapter(mostPopularAdapter);
    }

    private void showCategory() {
        mainCategoryAdapter = new MainCategoryAdapter(categories, 0);
        LinearLayoutManager horizontal_layout = new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        rv_main_categories.setLayoutManager(horizontal_layout);
        rv_main_categories.setAdapter(mainCategoryAdapter);
    }

}