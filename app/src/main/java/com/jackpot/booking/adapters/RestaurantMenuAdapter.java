package com.jackpot.booking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.R;
import com.jackpot.booking.models.Menu;

import java.util.List;

public class RestaurantMenuAdapter extends RecyclerView.Adapter<RestaurantMenuAdapter.MyViewHolder> {

    private Context context;
    private List<Menu> menus;

    public RestaurantMenuAdapter(Context context, List<Menu> menus) {
        this.context = context;
        this.menus = menus;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.shop_product_two_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Menu menu = menus.get(position);

        if ( menu == null )
            return;

        holder.tv_product.setText(menu.getTitle());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void updateData(List<Menu> menus) {
        this.menus.clear();
        this.menus.addAll(menus);

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return menus.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_product;
        LinearLayout ll_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_product = itemView.findViewById(R.id.tv_shop_product_2);
            ll_item = itemView.findViewById(R.id.ll_product_item);
        }
    }
}
