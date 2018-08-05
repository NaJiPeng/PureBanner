package com.njp.library;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片展示的RecyclerView的adapter
 */
class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    private List<Object> mImages = new ArrayList<>();
    private BannerImageLoader mImageLoader;
    private OnBannerItemClickListener mItemClickListener;
    private int mWidth = 0;
    private int mPreviousNextMargin = 0;
    private int mItemMargin = 0;
    private float mItemCornerRadius = 0.0f;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        /**
         * 设置视图
         */
        holder.mCardView.setRadius(mItemCornerRadius);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.mCardView.getLayoutParams();
        params.leftMargin = params.rightMargin = mItemMargin / 2;
        if (mWidth != 0) {
//            item的宽度 = 整个recyclerView的宽度 - 2*前后item露出的距离 - 2*item间距
            params.width = mWidth - 2 * mPreviousNextMargin - 2 * mItemMargin;
        }
        /**
         * 加载图片
         */
        if (mImageLoader != null) {
            mImageLoader.displayImage(holder.mImageView, mImages.get(position % mImages.size()));
        }
        /**
         * 设置监听器
         */
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(position % mImages.size());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;     //‘无限滚动’实现
    }

    void setImages(List<Object> images) {
        mImages.clear();
        mImages.addAll(images);
        notifyDataSetChanged();
    }

    void setPreviousNextMargin(int previousNextMargin) {
        if (previousNextMargin != mPreviousNextMargin) {
            mPreviousNextMargin = previousNextMargin;
            notifyDataSetChanged();
        }
    }

    void setItemMargin(int itemMargin) {
        if (itemMargin != mItemMargin) {
            mItemMargin = itemMargin;
            notifyDataSetChanged();
        }
    }

    void setItemCornerRadius(float itemCornerRadius) {
        if (itemCornerRadius != mItemCornerRadius) {
            mItemCornerRadius = itemCornerRadius;
            notifyDataSetChanged();
        }
    }

    void setWidth(int width) {
        if (width != mWidth) {
            mWidth = width;
            notifyDataSetChanged();
        }
    }

    void setImageLoader(BannerImageLoader imageLoader) {
        mImageLoader = imageLoader;
    }

    void setItemClickListener(OnBannerItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        ImageView mImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_pure_banner);
            mCardView = itemView.findViewById(R.id.item);

        }
    }
}
