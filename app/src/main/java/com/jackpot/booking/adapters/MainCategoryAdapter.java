package com.jackpot.booking.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.R;
import com.jackpot.booking.activities.RestaurantCategories;
import com.jackpot.booking.activities.ShopCategories;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MyViewHolder> {

    private Context context;
    private List<Category> categories;
    private int type;

    public MainCategoryAdapter(List<Category> categories, int type) {
        this.categories = categories;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.main_category_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categories.get(position);

        if ( position == 0 ) {
            holder.ll_item.getBackground().setTint(context.getResources().getColor(R.color.colorPrimary));
        }
        else {
            holder.ll_item.getBackground().setTint(context.getResources().getColor(R.color.border_color));
        }

        Picasso.with(context).load(G.DOMAIN_URL + category.getImage())
                .fit()
                .placeholder(R.drawable.healthy_eating)
                .into(holder.iv_item);

//        holder.iv_item.setImageResource(category.getImage());
        holder.tv_name.setText(category.getName());
        String strPlaces = String.format("%d places", category.getNumbers());
        holder.tv_places.setText(strPlaces);
        holder.ll_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

    public void updateData(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_item;
        TextView tv_name;
        TextView tv_places;
        RelativeLayout ll_row;
        LinearLayout ll_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_item = itemView.findViewById(R.id.iv_main_cate);
            tv_name = itemView.findViewById(R.id.tv_main_cate_name);
            tv_places = itemView.findViewById(R.id.tv_main_cate_places);

            ll_row = itemView.findViewById(R.id.ll_category_row);
            ll_item = itemView.findViewById(R.id.iv_main_cate_item);
        }
    }
}
