package com.njp.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * Banner主类
 */
public class Banner extends RelativeLayout {

    private RecyclerView mBanner;
    private BannerAdapter mBannerAdapter = new BannerAdapter();
    private ScrollSpeedLinearLayoutManger mBannerLayoutManager = new ScrollSpeedLinearLayoutManger(getContext(), LinearLayoutManager.HORIZONTAL, false);

    private RecyclerView mIndicator;
    private IndicatorAdapter mIndicatorAdapter = new IndicatorAdapter();

    private int mCount;
    private int mInterval;
    private int mCurrent;
    private boolean mAutoPlay;

    private ViewPager.PageTransformer mPageTransformer;

    private Runnable mRunnableAutoPlay = new Runnable() {
        @Override
        public void run() {
            mBanner.smoothScrollToPosition(++mCurrent);

        }
    };

    /**
     * 构造器
     *
     * @param context
     * @param attrs
     */
    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.banner, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        getAttrs(typedArray);
        typedArray.recycle();
        init();
    }

    /**
     * 构造器
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.banner, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Banner);
        getAttrs(typedArray);
        typedArray.recycle();
        init();
    }

    /**
     * 构造器
     *
     * @param context
     */
    public Banner(Context context) {
        super(context);
        inflate(context, R.layout.banner, this);
        init();
    }

    /**
     * 设置图片数据源
     *
     * @param images
     */
    public void setUrls(List<Object> images) {
        mBannerAdapter.setImages(images);
        mCount = images.size();
        mCurrent = mCount * 10000000;
        mBanner.scrollToPosition(mCurrent - 1);    //强制渲染前一项的视图
        mBanner.smoothScrollToPosition(mCurrent);    //保证前后都‘无限’滚动

        mIndicatorAdapter.setCount(images.size());
        mIndicatorAdapter.setCurrent(0);

        if (mAutoPlay) {
            postDelayed(mRunnableAutoPlay, mInterval);
        }
    }

    /**
     * 设置指示器大小
     *
     * @param indicatorSize
     */
    public void setIndicatorSize(int indicatorSize) {
        mIndicatorAdapter.setIndicatorSize(indicatorSize);
    }

    /**
     * 设置指示器默认颜色
     *
     * @param defaultColor
     */
    public void setDefaultIndicatorColor(int defaultColor) {
        mIndicatorAdapter.setColorFalse(defaultColor);
    }

    /**
     * 设置指示器选中颜色
     *
     * @param activeColor
     */
    public void setActiveIndicatorColor(int activeColor) {
        mIndicatorAdapter.setColorTrue(activeColor);
    }

    /**
     * 设置自动切换的周期时间
     *
     * @param interval
     */
    public void setInterval(int interval) {
        mInterval = interval;
        if (mAutoPlay) {
            removeCallbacks(mRunnableAutoPlay);
            postDelayed(mRunnableAutoPlay, interval);
        }
    }

    /**
     * 设置自动滚动的滚动时间
     *
     * @param duration
     */
    public void setDuration(final int duration) {
        post(new Runnable() {
            @Override
            public void run() {
                mBannerLayoutManager.setCalculateSpeedPerPixel((float) duration / getWidth());
            }
        });
    }

    /**
     * 设置图片加载器
     *
     * @param imageLoader
     */
    public void setImageLoader(BannerImageLoader imageLoader) {
        mBannerAdapter.setImageLoader(imageLoader);
    }

    public void setItemClickListener(OnBannerItemClickListener listener) {
        mBannerAdapter.setItemClickListener(listener);
    }

    /**
     * 设置是否自动播放
     *
     * @param autoPlay
     */
    public void setAutoPlay(boolean autoPlay) {
        mAutoPlay = autoPlay;
        if (autoPlay) {
            postDelayed(mRunnableAutoPlay, mInterval);
        } else {
            removeCallbacks(mRunnableAutoPlay);
        }
    }

    /**
     * 设置前后item露出的距离
     *
     * @param previousNextMargin
     */
    public void setPreviousNextMargin(final int previousNextMargin) {
        mBannerAdapter.setPreviousNextMargin(previousNextMargin);
    }


    /**
     * 设置item间距
     *
     * @param itemMargin
     */
    public void setItemMargin(int itemMargin) {
        mBannerAdapter.setItemMargin(itemMargin);
    }

    /**
     * 设置item圆角
     *
     * @param itemCornerRadius
     */
    public void setItemCornerRadius(float itemCornerRadius) {
        mBannerAdapter.setItemCornerRadius(itemCornerRadius);
    }

    /**
     * 设置翻页动画效果
     *
     * @param transformer
     */
    public void setPageTransformer(ViewPager.PageTransformer transformer) {
        mPageTransformer = transformer;
    }

    /**
     * 获取自定义属性
     *
     * @param typedArray
     */
    private void getAttrs(TypedArray typedArray) {
        boolean autoPlay = typedArray.getBoolean(R.styleable.Banner_autoPlay, true);
        int interval = typedArray.getInt(R.styleable.Banner_interval, 3000);
        int duration = typedArray.getInt(R.styleable.Banner_duration, 200);
        setAutoPlay(autoPlay);
        setInterval(interval);
        setDuration(duration);


        int indicatorSize = typedArray.getDimensionPixelSize(R.styleable.Banner_indicatorSize, dp2px(8));
        int defaultColor = typedArray.getColor(R.styleable.Banner_defaultIndicatorColor, Color.parseColor("#aa949494"));
        int activeColor = typedArray.getColor(R.styleable.Banner_activeIndicatorColor, Color.parseColor("#ffffff"));
        setIndicatorSize(indicatorSize);
        setDefaultIndicatorColor(defaultColor);
        setActiveIndicatorColor(activeColor);

        int previousNextMargin = typedArray.getDimensionPixelOffset(R.styleable.Banner_previousNextMargin, 0);
        int itemMargin = typedArray.getDimensionPixelOffset(R.styleable.Banner_itemMargin, 0);
        float itemCornerRadius = typedArray.getDimension(R.styleable.Banner_itemCornerRadius, 0);
        setPreviousNextMargin(previousNextMargin);
        setItemMargin(itemMargin);
        setItemCornerRadius(itemCornerRadius);
    }

    /**
     * 初始化工作
     */
    private void init() {
        /**
         * banner图初始化
         */
        mBanner = findViewById(R.id.pure_banner);
        mBanner.setLayoutManager(mBannerLayoutManager);
        mBanner.setAdapter(mBannerAdapter);
        new PagerSnapHelper().attachToRecyclerView(mBanner);    //实现ViewPager效果
        /**
         * 指示器初始化
         */
        mIndicator = findViewById(R.id.pure_indicator);
        mIndicator.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        mIndicator.setAdapter(mIndicatorAdapter);
        /**
         * 将banner图和指示器相关联
         */
        mBanner.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case SCROLL_STATE_IDLE:
                        if (mAutoPlay) {
                            postDelayed(mRunnableAutoPlay, mInterval);     //滑动结束开始自动播放
                        }
                        break;
                    case SCROLL_STATE_DRAGGING:
                    case SCROLL_STATE_SETTLING:
                        removeCallbacks(mRunnableAutoPlay);    //滑动过程中禁用自动播放
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int first = mBannerLayoutManager.findFirstVisibleItemPosition();
                int last = mBannerLayoutManager.findLastVisibleItemPosition();
                /**
                 * 改变当前选中item
                 */
                if (last - first < 2) {
                    mCurrent = dx < 0 ? first : last;
                    mIndicatorAdapter.setCurrent(mCurrent % mCount);
                }
                /**
                 * 回调item动画监听
                 */
                if (mPageTransformer == null) {
                    return;
                }
                switch (last - first) {
                    case 0:
                        View current = mBannerLayoutManager.findViewByPosition(first);
                        View left = mBannerLayoutManager.findViewByPosition(first - 1);
                        View right = mBannerLayoutManager.findViewByPosition(last + 1);
                        mPageTransformer.transformPage(current, 0);
                        if (left != null) {
                            mPageTransformer.transformPage(left, -1);
                        }
                        if (right != null) {
                            mPageTransformer.transformPage(right, 1);
                        }
                        break;
                    case 2:
                        View currentView = mBannerLayoutManager.findViewByPosition((last + first) / 2);
                        float currentOffset = getOffset(currentView);
                        mPageTransformer.transformPage(currentView, currentOffset);
                    case 1:
                        View leftView = mBannerLayoutManager.findViewByPosition(first);
                        View rightView = mBannerLayoutManager.findViewByPosition(last);
                        float leftOffset = getOffset(leftView);
                        float rightOffset = getOffset(rightView);
                        mPageTransformer.transformPage(leftView, leftOffset);
                        mPageTransformer.transformPage(rightView, rightOffset);
                        break;
                    default:
                        break;
                }
            }
        });
        /**
         * 将recyclerView的宽度通知给banner
         */
        post(new Runnable() {
            @Override
            public void run() {
                mBannerAdapter.setWidth(getWidth());
            }
        });
    }

    /**
     * 视图销毁取消回调
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(mRunnableAutoPlay);
        mPageTransformer = null;
    }

    /**
     * 获取item偏移量的方法
     *
     * @param itemView
     * @return
     */
    private float getOffset(View itemView) {
        return ((float) (itemView.getLeft() + itemView.getRight()) / 2 - getWidth() / 2) / itemView.getWidth();
    }

    /**
     * dp转换成px的方法
     *
     * @param dp
     * @return
     */
    private int dp2px(int dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp + 0.5f);
    }
}
