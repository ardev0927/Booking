package com.jackpot.booking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.R;
import com.jackpot.booking.activities.CartActivity;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Product;

import java.util.List;

public class ShopProductAdapter extends RecyclerView.Adapter<ShopProductAdapter.MyViewHolder> {

    private Context context;
    private List<Product> products;

    public ShopProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ShopProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.shop_product_two_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopProductAdapter.MyViewHolder holder, int position) {

        Product product = products.get(position);

        if ( product == null )
            return;

        holder.tv_product.setText(product.getTitle());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartActivity.class);
                intent.putExtra(G.PRODUCT_ID, product.getId());
                intent.putExtra(G.PRODUCT_NAME, product.getTitle());
                context.startActivity(intent);

            }
        });
    }

    public void updateData(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return products.size();
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
