package com.smile.recyclerview;

/**为RecycleView添加点击事件
 * @author smile
 * @project OnRecyclerViewListener
 * @date 2016-03-03-16:39
 */
public interface OnRecyclerViewListener {

    /**
     * 点击事件
     * @param position
     */
    void onItemClick(int position);

    /**
     * 长按事件
     * @param position
     * @return
     */
    boolean onItemLongClick(int position);

}
