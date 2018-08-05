package com.njp.library.transformers;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

/**
 * 绕下面旋转的动画效果
 */
public class RotateDownPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(@NonNull View view, float position) {
        view.setPivotX(position > 0 ? view.getWidth() * 0.5f * (1 - position) : view.getWidth() * (0.5f + 0.5f * (-position)));
        view.setPivotY(view.getHeight());
        view.setRotation(position * 15);
    }
}
