package com.jackpot.booking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.R;

import java.util.ArrayList;

public class PreviousBookingAdapter extends RecyclerView.Adapter<PreviousBookingAdapter.PBViewHolder> {

    private ArrayList<String> titles, descriptions, counts, categories, prices;
    private Context context;

    public PreviousBookingAdapter(Context context, ArrayList<String> titles,
                                  ArrayList<String> descriptions,ArrayList<String> counts,
                                  ArrayList<String> categories, ArrayList<String> prices) {
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
        this.categories = categories;
        this.prices = prices;
        this.counts = counts;
    }

    @NonNull
    @Override
    public PBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.previousbook_row, parent, false);
        return new PBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PBViewHolder holder, int position) {
        holder.tv_title.setText(titles.get(position));
        holder.tv_desc.setText(descriptions.get(position));
        holder.tv_count.setText(counts.get(position));
        holder.tv_category.setText(categories.get(position));
        holder.tv_price.setText(prices.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class PBViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_desc, tv_count, tv_category, tv_price;

        public PBViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.iv_prevbook_title);
            tv_desc = itemView.findViewById(R.id.tv_prevbook_desc);
            tv_count = itemView.findViewById(R.id.tv_prevbook_count);
            tv_category = itemView.findViewById(R.id.tv_prevbook_category);
            tv_price = itemView.findViewById(R.id.tv_prevbook_price);

        }
    }
}
