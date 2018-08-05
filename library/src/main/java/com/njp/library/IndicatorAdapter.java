package com.njp.library;

import android.graphics.drawable.GradientDrawable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * 指示器的adapter
 */
class IndicatorAdapter extends RecyclerView.Adapter<IndicatorAdapter.ViewHolder> {

    private int mCurrent;
    private int mCount;
    private int mIndicatorSize;
    private int mColorFalse;
    private int mColorTrue;

    /**
     * 数据发生变化
     *
     * @param count
     */
    void setCount(int count) {
        if (count != mCount) {
            mCount = count;
            mCurrent = 0;
            notifyDataSetChanged();
        }
    }

    /**
     * 指示点变化
     *
     * @param current
     */
    void setCurrent(int current) {
        if (current != mCurrent) {
            mCurrent = current;
            notifyDataSetChanged();
        }
    }

    void setColorTrue(int colorTrue) {
        if (colorTrue != mColorTrue) {
            mColorTrue = colorTrue;
            notifyDataSetChanged();
        }
    }

    void setColorFalse(int colorFalse) {
        if (colorFalse != mColorFalse) {
            mColorFalse = colorFalse;
            notifyDataSetChanged();
        }
    }

    public void setIndicatorSize(int indicatorSize) {
        if (indicatorSize != mIndicatorSize) {
            mIndicatorSize = indicatorSize;
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_indicator, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GradientDrawable drawable = (GradientDrawable) holder.mImageView.getDrawable();
        drawable.setColor(position == mCurrent ? mColorTrue : mColorFalse);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.width = layoutParams.height = mIndicatorSize;
        layoutParams.leftMargin = layoutParams.rightMargin = mIndicatorSize / 3;
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_pure_indicator);
        }
    }
}
