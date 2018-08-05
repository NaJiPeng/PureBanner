# PureBanner
一个简介易用的安卓Banner库，高度灵活可定制。
支持无限滚动，自定义指示器的大小和颜色，自动轮播，自定义轮播时长，图片间距，圆角，前后图片露出的距离，切换动画等等。。。
# 使用步骤
## Step 1.添加依赖

	//Add it in your root build.gradle at the end of repositories
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
	//Add the dependency
	dependencies {
	        implementation 'com.github.NaJiPeng:PureBanner:v1.2.0'
	}
  
## Step 2.在布局文件中引用

	<com.njp.purebannerlib.PureBanner
        	android:id="@+id/banner"
        	android:layout_width="match_parent"
        	android:layout_height="200dp"
        	app:defaultIndicatorColor="#aa949494"
        	app:selectedIndicatorColor="#ffffff"
        	app:autoPlay="true"
        	app:autoPlayDuration="4000"/>
       
## Step 3.Java代码中使用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PureBanner banner = findViewById(R.id.banner);
        banner.setImageLoader(new BannerImageLoader() {
            @Override
            public void displayImage(ImageView imageView, String url) {
                Glide.with(MainActivity.this).load(url).into(imageView);
            }
        });
        String[] images = {
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-157639.jpg",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-345074.jpg",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-217326.jpg",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-259115.png",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-329174.jpg",
                "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-33428.png"
        };
        banner.setUrls(Arrays.asList(images));

        banner.setItemClickListener(new OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
## 支持的自定义属性 ##
| 属性名 | 作用 | 格式 | 默认值 |
| ------ | ------ | ------ | ------ |
| indicatorSize | 指示器圆点大小 | dimension | 8dp |
| defaultIndicatorColor | 指示器未选中的圆点颜色 | color | #aa949494 |
| activeIndicatorColor | 指示器选中的圆点颜色 | color | #ffffff |
| autoPlay | 是否自动播放 | boolean | true |
| autoPlayDuration | 自动播放的周期毫秒数 | integer | 3000 |
| previousNextMargin | 前后图片露出的距离 | dimension | 0 |
| itemMargin | 相邻两个图片的间距 | dimension | 0 |
| itemCornerRadius | 图片的圆角半径 | dimension | 0 |
## 接口方法 ##
| 方法 | 作用 |
| ------ | ------ | 
| setImages(List<Object> images) | 设置图片数据 | 
| setIndicatorSize(int indicatorSize) | 设置指示器大小 | 
| setDefaultIndicatorColor(int defaultColor) | 设置未选中的指示器圆点颜色 | 
| setActiveIndicatorColor(int activeColor) | 设置选中的指示器圆点颜色 | 
| setAutoPlay(boolean autoPlay) | 设置是否自动轮播 | 
| setAutoPlayDuration(int duration) | 设置自动播放的周期时间毫秒数 | 
| setImageLoader(BannerImageLoader imageLoader) | 设置图片加载器 | 
| setItemClickListener(OnBannerItemClickListener listener) | 设置点击事件监听器 | 
| setPreviousNextMargin(int previousNextMargin) | 设置前后图片露出的距离 | 
| setItemMargin(int itemMargin) | 设置图片间距 | 
| setItemCornerRadius(float itemCornerRadius) | 设置图片圆角半径 | 
| setOnItemViewOffsetListener(OnItemViewOffsetListener listener) | 设置图片偏移监听器(可自定义动画) | 
