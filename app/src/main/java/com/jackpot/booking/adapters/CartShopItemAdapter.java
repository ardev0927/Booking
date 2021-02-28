package com.jackpot.booking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.R;
import com.jackpot.booking.activities.CartActivity;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartShopItemAdapter extends RecyclerView.Adapter<CartShopItemAdapter.MyViewHolder>{

    private Context context;
    List<Product> products;

    public CartShopItemAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }
    @NonNull
    @Override
    public CartShopItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cart_shop_item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartShopItemAdapter.MyViewHolder holder, int position) {

        final Product pt = products.get(position);
        Picasso.with(context).load(G.DOMAIN_URL + pt.getPicture())
                .fit().centerCrop()
                .placeholder(R.drawable.rest_rect_7)
                .into(holder.iv_item);

        holder.cd_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartActivity.class);
                intent.putExtra(G.PRODUCT_ID, pt.getId());
                intent.putExtra(G.PRODUCT_NAME, pt.getTitle());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_item;
        CardView cd_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_item = itemView.findViewById(R.id.iv_shop_cart_item);
            cd_item = itemView.findViewById(R.id.cd_shop_item);
        }
    }
}
