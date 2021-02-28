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
import com.jackpot.booking.models.Categorie;

import java.util.List;

public class ShopCategoryAdapter extends RecyclerView.Adapter<ShopCategoryAdapter.MyViewHolder> {

    private Context context;
    private List<Categorie> categories;
    private ShopProductAdapter productAdapter;

    public ShopCategoryAdapter(Context context, List<Categorie> categories, ShopProductAdapter productAdapter) {
        this.context = context;
        this.categories = categories;
        this.productAdapter = productAdapter;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.shop_product_one_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Categorie categorie = categories.get(position);

        if ( categorie == null )
            return;

        holder.tv_category.setText(categorie.getName());
        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( productAdapter != null )
                    productAdapter.updateData(categorie.getProducts());
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_category;
        LinearLayout ll_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_category = itemView.findViewById(R.id.tv_shop_product_1);
            ll_item = itemView.findViewById(R.id.ll_menu_product_item);
        }
    }
}
