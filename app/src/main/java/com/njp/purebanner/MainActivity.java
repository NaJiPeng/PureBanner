package com.njp.purebanner;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.njp.library.BannerImageLoader;
import com.njp.library.OnBannerItemClickListener;
import com.njp.purebanner.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private ObservableBoolean mAutoPlay = new ObservableBoolean(true);
    private ObservableInt mInterval = new ObservableInt(3000);
    private ObservableInt mIndicatorSize = new ObservableInt(30);
    private ObservableInt mActiveIndicatorColor = new ObservableInt(Color.parseColor("#ffffff"));
    private ObservableInt mDefaultIndicatorColor = new ObservableInt(Color.parseColor("#aa949494"));
    private ObservableInt mPreviousNextMargin = new ObservableInt(0);
    private ObservableInt mItemMargin = new ObservableInt(0);
    private ObservableInt mItemCornerRadius = new ObservableInt(0);
    private ObservableInt mDuration = new ObservableInt(200);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mBinding.setAutoPlay(mAutoPlay);
        mBinding.setInterval(mInterval);
        mBinding.setIndicatorSize(mIndicatorSize);
        mBinding.setDefaultIndicatorColor(mDefaultIndicatorColor);
        mBinding.setActiveIndicatorColor(mActiveIndicatorColor);
        mBinding.rgIndicatorColor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_white:
                        mActiveIndicatorColor.set(Color.parseColor("#ffffff"));
                        break;
                    case R.id.rb_green:
                        mActiveIndicatorColor.set(Color.parseColor("#37ff00"));
                        break;
                    case R.id.rb_pink:
                        mActiveIndicatorColor.set(Color.parseColor("#d6ff2462"));
                        break;
                    case R.id.rb_blue:
                        mActiveIndicatorColor.set(Color.parseColor("#d600aeff"));
                        break;
                    default:
                        break;
                }
            }
        });
        mBinding.rbWhite.setChecked(true);
        mBinding.setItemMargin(mItemMargin);
        mBinding.setPreviousNextMargin(mPreviousNextMargin);
        mBinding.setItemCornerRadius(mItemCornerRadius);
        mBinding.setDuration(mDuration);

        mBinding.banner.setImageLoader(new BannerImageLoader() {
            @Override
            public void displayImage(ImageView imageView, Object image) {
                Glide
                        .with(MainActivity.this)
                        .load(image)
                        .into(imageView);
            }
        });

        mBinding.banner.setUrls(Arrays.<Object>asList(
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-217326.jpg",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-272605.jpg",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-498197.jpg",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-636420.jpg",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-372250.jpg",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-313313.png",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-540754.jpg"
        ));

        mBinding.banner.setItemClickListener(new OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "Item" + position + " is clicked !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void toAnimationActivity(View view) {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }


}