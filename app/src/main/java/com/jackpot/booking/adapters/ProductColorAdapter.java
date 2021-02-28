package com.jackpot.booking.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.R;
import com.jackpot.booking.models.ProductColor;

import java.util.List;

public class ProductColorAdapter extends RecyclerView.Adapter<ProductColorAdapter.MyViewHolder> {

    private Context context;
    private List<ProductColor> productColors;

    public ProductColorAdapter(List<ProductColor> productColors) {
        this.productColors = productColors;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.product_color_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ProductColor productColor = productColors.get(position);

        assert productColor != null;

        holder.rb_color.setButtonTintList(ColorStateList.valueOf(Color.parseColor(productColor.getValue())));
    }

    @Override
    public int getItemCount() {
        return productColors.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_color_row;
        RadioButton rb_color;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_color_row = itemView.findViewById(R.id.ll_product_color);
            rb_color = itemView.findViewById(R.id.rb_product_color);
        }
    }
}
