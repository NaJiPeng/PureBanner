package com.njp.library.transformers;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * 放大缩小的动画效果
 */
public class ScaleInOutPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View view, float position) {
        view.setPivotX(position > 0 ? view.getWidth() * 0.5f * (1 - position) : view.getWidth() * (0.5f + 0.5f * (-position)));
        view.setPivotY(view.getHeight() / 2);
        view.setScaleY(1 - 0.2f * Math.abs(position));
        view.setScaleX(1 - 0.2f * Math.abs(position));
    }
}
