package com.jackpot.booking.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jackpot.booking.R;

import java.util.Objects;

class SkipViewPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private Context context;
    private int[] logo_images;
    private int[] next_images;
    private LayoutInflater mLayoutInflater;
    private int[] logos_text;
    ViewPager viewPager;
    TextView tv_skip;

    public SkipViewPagerAdapter(Context context, int[] logo_images, int[] tv_logo, int[] next_images) {
        this.context = context;
        this.logo_images = logo_images;
        this.next_images = next_images;
        this.logos_text = tv_logo;

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return logo_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.skip_item, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.skip_logo);
        TextView tv_logo = itemView.findViewById(R.id.skip_logo_text);
        ImageView iv_logo_next = itemView.findViewById(R.id.skip_logo_next);

        // setting the image in the imageView
        imageView.setImageResource(logo_images[position]);
        iv_logo_next.setImageResource(next_images[position]);
        iv_logo_next.setOnClickListener(this);
        tv_logo.setText(logos_text[position]);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    public void setViewPager(ViewPager vp, TextView tv) {
        tv_skip = tv;
        viewPager = vp;
    }

    @Override
    public void onClick(View v) {
        int cur_item = viewPager.getCurrentItem();
        if ( cur_item == 0 ) {
            tv_skip.setText("Next");
            viewPager.setCurrentItem(cur_item + 1);
        } else {
            tv_skip.setText("Skip");
            viewPager.setCurrentItem(cur_item - 1);
        }
    }
}
