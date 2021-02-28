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
import com.jackpot.booking.models.Product;
import com.jackpot.booking.models.ProductSize;

import java.util.List;

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.MyViewHolder> {

    private Context context;
    private List<ProductSize> productSizes;

    public ProductSizeAdapter(List<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }

    @NonNull
    @Override
    public ProductSizeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.product_size_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSizeAdapter.MyViewHolder holder, int position) {

        ProductSize productSize = productSizes.get(position);
        assert productSize != null;

        holder.tv_size.setText(productSize.getValue());
    }

    @Override
    public int getItemCount() {
        return productSizes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_row;
        TextView tv_size;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_row = itemView.findViewById(R.id.ll_product_size);
            tv_size = itemView.findViewById(R.id.tv_product_size);
        }
    }
}
