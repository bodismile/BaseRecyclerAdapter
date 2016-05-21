package com.smile.recyclerview.util;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class DisplayConfig {

    /**UIL默认的显示配置:圆角
     * @param defaultRes
     * @return
     */
    public static DisplayImageOptions getDefaultOptions(boolean hasRounded,int defaultRes){
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true);
        if(hasRounded){
            builder.displayer(new RoundedBitmapDisplayer(12));
        }
        if(defaultRes!=0){
            builder.showImageForEmptyUri(defaultRes)
                    .showImageOnFail(defaultRes);
        }
        return builder.build();//构建完成
    }
}
