package com.jackpot.booking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.R;
import com.jackpot.booking.helper.G;
import com.jackpot.booking.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RelativeProductAdapter extends RecyclerView.Adapter<RelativeProductAdapter.MyViewHolder> {

    private Context context;
    List<Product> products;
    final private ListItemClickListener  mOnClickListener;

    public interface ListItemClickListener  {
        void onListItemClick(int position);
    }

    public RelativeProductAdapter(ListItemClickListener mOnClickListener, List<Product> products) {
        this.products = products;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.cart_shop_item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = products.get(position);
        if ( product != null ) {
            Picasso.with(context).load(G.DOMAIN_URL + product.getPicture())
                    .fit().centerCrop()
                    .placeholder(R.drawable.rest_rect_7)
                    .into(holder.iv_item);

        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView iv_item;
        CardView cd_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            iv_item = itemView.findViewById(R.id.iv_shop_cart_item);
            cd_item = itemView.findViewById(R.id.cd_shop_item);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }
    }
}
