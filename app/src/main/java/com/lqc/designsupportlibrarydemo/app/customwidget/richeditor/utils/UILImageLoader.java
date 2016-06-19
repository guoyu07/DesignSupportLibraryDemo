package com.lqc.designsupportlibrarydemo.app.customwidget.richeditor.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import cn.finalteam.galleryfinal.widget.GFImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

/**
 * Created by albert on 16-6-20.
 */
public class UILImageLoader implements cn.finalteam.galleryfinal.ImageLoader{

    private Bitmap.Config mImageConfig;

    public UILImageLoader() {
        this(Bitmap.Config.RGB_565);
    }

    public UILImageLoader(Bitmap.Config config) {
        this.mImageConfig = config;
    }

    //TODO:这里图片加载与定制的

    @Override
    public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(false)
                .cacheInMemory(false)
                .bitmapConfig(mImageConfig)
                .build();
        ImageSize imageSize = new ImageSize(width, height);
        ImageLoader.getInstance().displayImage("file://" + path, new ImageViewAware(imageView), options, imageSize, null, null);
    }

    @Override
    public void clearMemoryCache() {

    }

}
