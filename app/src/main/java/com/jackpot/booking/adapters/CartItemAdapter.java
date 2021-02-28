package com.jackpot.booking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.activities.CartActivity;
import com.jackpot.booking.R;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Menu;
import com.jackpot.booking.models.Picture;
import com.jackpot.booking.models.RestaurantMenu;
import com.jackpot.booking.models.RestuatrantImage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.MyViewHolder> {

    private Context context;
    int type;
    List<Menu> restaurantMenus;

    public CartItemAdapter(Context context, List<Menu> restaurantMenus, int type) {
        this.context = context;
        this.restaurantMenus = restaurantMenus;
        this.type = type;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cart_item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdapter.MyViewHolder holder, int position) {
        holder.type = type;

        final Menu menu = restaurantMenus.get(position);
        Picasso.with(context).load(G.DOMAIN_URL + menu.getPicture())
                .fit().centerCrop()
                .placeholder(R.drawable.rest_rect_7)
                .into(holder.iv_item);

        holder.cd_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

//        holder.iv_item.setBackground(ContextCompat.getDrawable(context, images[position]));
    }

    @Override
    public int getItemCount() {
        return restaurantMenus.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_item;
        CardView cd_item;
        int type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_item = itemView.findViewById(R.id.iv_cart_item);
            cd_item = itemView.findViewById(R.id.cd_item);
        }
    }
}
