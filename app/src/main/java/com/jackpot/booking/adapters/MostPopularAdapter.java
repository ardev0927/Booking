package com.jackpot.booking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.jackpot.booking.R;
import com.jackpot.booking.activities.RestuarantActivity;
import com.jackpot.booking.activities.ShopActivity;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Popular;
import com.jackpot.booking.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.MyViewHolder> {

    private Context context;
    private int type;
    List<Popular> populars;

    public MostPopularAdapter(Context context, List<Popular> populars, int type) {
        this.context = context;
        this.populars = populars;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.most_popular_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.type = type;

        final Popular popular = populars.get(position);

        Picasso.with(context).load(G.DOMAIN_URL + popular.getMain_image())
                .fit().centerCrop()
                .placeholder(R.drawable.rest_rect_7)
                .into(holder.iv_item);

        holder.tv_title.setText(popular.getName());
        holder.tv_desc.setText(popular.getAddress());
        String str_rating = String.format("%.1f", popular.getRating());
        holder.tv_rating.setText(str_rating);
        String str_ratings = String.format("(%d ratings)", popular.getRating_number());
        holder.tv_max_ratings.setText(str_ratings);

        holder.lt_popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json_string = gson.toJson(popular);
                Intent intent;
                if (type == 0) {
                    intent = new Intent(context, RestuarantActivity.class);
                    intent.putExtra(G.REST_ID, popular.getId());
                }
                else {
                    intent = new Intent(context, ShopActivity.class);
                    intent.putExtra(G.SHOP_ID, popular.getId());
                }
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (populars == null)
            return 0;
        return populars.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_item;
        TextView tv_title;
        TextView tv_desc;
        TextView tv_rating;
        TextView tv_max_ratings;
        LinearLayout lt_popular;
        int type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            type = 0;
            iv_item = itemView.findViewById(R.id.iv_popular_item);
            tv_title = itemView.findViewById(R.id.tv_popular_name);
            tv_desc = itemView.findViewById(R.id.tv_popular_desc);
            tv_rating = itemView.findViewById(R.id.tv_popular_rating);
            tv_max_ratings = itemView.findViewById(R.id.tv_popular_ratings);
            lt_popular = itemView.findViewById(R.id.lt_most_popular);
            lt_popular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
