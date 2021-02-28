package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jackpot.booking.R;
import com.jackpot.booking.adapters.ExplorerCategoriesAdapter;
import com.jackpot.booking.helper.G;

public class ShowAllCategories extends AppCompatActivity {

    RecyclerView rv_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_categories);

        int type = getIntent().getIntExtra(G.EXP_CAT, 0);
        TextView tv_title = findViewById(R.id.tv_sac_title);

        ExplorerCategoriesAdapter adapter;
        if( type == 0 ) {
            tv_title.setText("Restaurant Categories");

            adapter = new ExplorerCategoriesAdapter(this, G.restCategories, 0);
        } else {
            tv_title.setText("Shop Categories");

            adapter = new ExplorerCategoriesAdapter(this, G.shopCategories, 1);
        }

        // Set categories
        rv_categories = findViewById(R.id.rv_explorer_categories);
        rv_categories.setLayoutManager(new GridLayoutManager(this, 3));
        rv_categories.setAdapter(adapter);
    }

    public void onBack(View view) {
        finish();
    }
}