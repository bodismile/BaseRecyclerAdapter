package com.smile.recyclerview;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.smile.recyclerview.util.DisplayConfig;

/**
 * 与BaseRecyclerAdapter一起使用，用于简化RecycleView使用
 * @author smile
 */
public class BaseRecyclerHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews;
    public  int layoutId;

    public BaseRecyclerHolder(int layoutId,View itemView) {
        super(itemView);
        this.layoutId =layoutId;
        this.mViews = new SparseArray<>(8);
    }

    public SparseArray<View> getAllView() {
        return mViews;
    }

    /**
     * @param viewId
     * @return
     */
    protected <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * @param viewId
     * @param text
     * @return
     */
    public BaseRecyclerHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * @param viewId
     * @param visibility
     * @return
     */
    public BaseRecyclerHolder setVisible(int viewId,int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    /**
     * @param viewId
     * @param drawableId
     * @return
     */
    public BaseRecyclerHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * @param viewId
     * @param bm
     * @return
     */
    public BaseRecyclerHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * @param avatar
     * @param defaultRes
     * @param viewId
     * @return
     */
    public BaseRecyclerHolder setImageView(String avatar, int defaultRes, int viewId) {
        ImageView iv = getView(viewId);
        if (!TextUtils.isEmpty(avatar)) {
            if (!avatar.equals(iv.getTag())) {//增加tag标记，减少UIL的display次数
                iv.setTag(avatar);
                //不直接display imageview改为ImageAware，解决ListView滚动时重复加载图片
                ImageAware imageAware = new ImageViewAware(iv, false);
                ImageLoader.getInstance().displayImage(avatar, imageAware, DisplayConfig.getDefaultOptions(true, defaultRes));
            }
        } else {
            iv.setImageResource(defaultRes);
        }
        return this;
    }

    //还可添加更多控件支持....
}