package com.jackpot.booking.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.R;
import com.jackpot.booking.activities.RestaurantCategories;
import com.jackpot.booking.activities.ShopCategories;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExplorerCategoriesAdapter extends RecyclerView.Adapter<ExplorerCategoriesAdapter.MyViewHolder> {

    private Context context;
    private List<Category> categories;
    private int type;

    public ExplorerCategoriesAdapter(Context context, List<Category> categories, int type) {
        this.context = context;
        this.categories = categories;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.main_category_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Category category = categories.get(position);

        // Set image
        Picasso.with(context).load(G.DOMAIN_URL + category.getImage())
                .fit().centerInside()
                .placeholder(R.drawable.healthy_eating)
                .into(holder.iv_main_cate);

        // Set category name
        holder.tv_category_name.setText(category.getName());

        // Set places
        holder.tv_places.setText(String.valueOf(category.getNumbers()));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout ll_row;
        ImageView iv_main_cate;
        TextView tv_category_name;
        TextView tv_places;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_row = itemView.findViewById(R.id.iv_main_cate_item);
            iv_main_cate = itemView.findViewById(R.id.iv_main_cate);
            tv_category_name = itemView.findViewById(R.id.tv_main_cate_name);
            tv_places = itemView.findViewById(R.id.tv_main_cate_places);

            ll_row.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Category category = categories.get(this.getAdapterPosition());
            Intent intent;
            if ( type == 0 ) {
                intent = new Intent(context, RestaurantCategories.class);
                intent.putExtra(G.REST_CAT, category.getName());
                intent.putExtra(G.REST_CAT_ID, category.getId());
            } else {
                intent = new Intent(context, ShopCategories.class);
                intent.putExtra(G.SHOP_CAT, category.getName());
                intent.putExtra(G.SHOP_CAT_ID, category.getId());
            }
            context.startActivity(intent);

        }
    }
}
