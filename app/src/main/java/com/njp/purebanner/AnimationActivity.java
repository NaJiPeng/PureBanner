package com.njp.purebanner;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.njp.library.BannerImageLoader;
import com.njp.library.transformers.CubeOutPageTransformer;
import com.njp.library.transformers.RotateDownPageTransformer;
import com.njp.library.transformers.RotateUpPageTransformer;
import com.njp.library.transformers.ScaleInOutPageTransformer;
import com.njp.purebanner.databinding.ActivityAnimationBinding;

import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity extends AppCompatActivity {

    private ActivityAnimationBinding mBinding;
    private List mUrls = Arrays.<Object>asList(
            "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-217326.jpg",
            "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-272605.jpg",
            "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-498197.jpg",
            "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-636420.jpg",
            "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-372250.jpg",
            "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-313313.png",
            "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-540754.jpg"
    );
    private BannerImageLoader mImageLoader = new BannerImageLoader() {
        @Override
        public void displayImage(ImageView imageView, Object image) {
            Glide.with(AnimationActivity.this).load(image).into(imageView);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_animation);

        mBinding.bannerScale.setImageLoader(mImageLoader);
        mBinding.bannerRotateUp.setImageLoader(mImageLoader);
        mBinding.bannerRotateDown.setImageLoader(mImageLoader);
        mBinding.bannerCube.setImageLoader(mImageLoader);
        mBinding.bannerScale.setUrls(mUrls);
        mBinding.bannerRotateUp.setUrls(mUrls);
        mBinding.bannerRotateDown.setUrls(mUrls);
        mBinding.bannerCube.setUrls(mUrls);

        mBinding.bannerScale.setPageTransformer(new ScaleInOutPageTransformer());
        mBinding.bannerRotateUp.setPageTransformer(new RotateUpPageTransformer());
        mBinding.bannerRotateDown.setPageTransformer(new RotateDownPageTransformer());
        mBinding.bannerCube.setPageTransformer(new CubeOutPageTransformer());


    }
}
