package com.jackpot.booking.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jackpot.booking.R;
import com.jackpot.booking.helper.PrefManager;

public class SkipActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SkipViewPagerAdapter mAdapter;
    private int[] logo_images = {R.drawable.group_300, R.drawable.group_301};
    private int[] next_images = {R.drawable.group_9265, R.drawable.group_9264};
    private int[] logo_texts = {R.string.skip_one_txt, R.string.skip_two_txt};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip);

        hideSystemUI();

        //
        final TextView tv_skip = findViewById(R.id.tv_skipnext);

        // Initializing the ViewPager Object
        mViewPager = findViewById(R.id.skip_view_pager);
        // Initializing the ViewPagerAdapter
        mAdapter = new SkipViewPagerAdapter(SkipActivity.this, logo_images, logo_texts, next_images);
        mAdapter.setViewPager(mViewPager, tv_skip);
        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if ( position == 0 )
                    tv_skip.setText("Skip");
                else
                    tv_skip.setText("Next");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        // skip button
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cur_item = mViewPager.getCurrentItem();
                if ( cur_item == 0 ) {
                    tv_skip.setText("Next");
                    mViewPager.setCurrentItem(cur_item + 1);
                } else {
                    PrefManager pm = new PrefManager(SkipActivity.this);
                    pm.setFirst(true);

                    startActivity(new Intent(getApplicationContext(), MainmenuActivity.class));
                    finish();
                }
            }
        });
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}