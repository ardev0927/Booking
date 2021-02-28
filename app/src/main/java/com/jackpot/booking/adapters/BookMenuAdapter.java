package com.jackpot.booking.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackpot.booking.R;
import com.jackpot.booking.models.BookMenu;

import java.util.List;

public class BookMenuAdapter extends RecyclerView.Adapter<BookMenuAdapter.MyViewHolder> {

    private Context context;
    private List<BookMenu> bookMenus;

    public BookMenuAdapter(List<BookMenu> bookMenus) {
        this.bookMenus = bookMenus;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.setting_menu_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        BookMenu bookMenu = bookMenus.get(position);

        if ( bookMenu != null ) {
            holder.tv_title.setText(bookMenu.getTitle());
            holder.tv_price.setText(String.format("Price: %s", bookMenu.getPrice()));
        }
    }

    @Override
    public int getItemCount() {
        return bookMenus.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout ll_layout;
        ImageView iv_picture;
        TextView tv_title;
        TextView tv_price;
        TextView tv_count;
        ImageView iv_plus;
        ImageView iv_minus;

        boolean is_selected;
        int count;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            ll_layout = itemView.findViewById(R.id.ll_menu_row);
            iv_picture = itemView.findViewById(R.id.iv_menu_picture);
            tv_title = itemView.findViewById(R.id.tv_menu_name);
            tv_price = itemView.findViewById(R.id.tv_menu_price);
            tv_count = itemView.findViewById(R.id.tv_menu_count);
            iv_plus = itemView.findViewById(R.id.iv_menu_plus_count);
            iv_minus = itemView.findViewById(R.id.iv_menu_minus_count);

            iv_plus.setOnClickListener(this);
            iv_minus.setOnClickListener(this);

            is_selected = false;
            count = 1;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_menu_row:
                    is_selected = !is_selected;
                    int padding = 15;
                    if ( is_selected ) {
                        ll_layout.setBackgroundResource(R.drawable.rounded_color);
                    }
                    else {
                        ll_layout.setBackgroundResource(R.drawable.normal_rounded_color);
                    }
                    ll_layout.setPadding(padding, padding, padding, padding);
                    break;
                case R.id.iv_menu_plus_count:
                    if ( is_selected ) {
                        count += 1;
                        tv_count.setText(String.valueOf(count));
                    }
                    break;
                case R.id.iv_menu_minus_count:
                    if( is_selected && count > 1 ) {
                        count -= 1;
                        tv_count.setText(String.valueOf(count));
                    }
            }
        }
    }
}
