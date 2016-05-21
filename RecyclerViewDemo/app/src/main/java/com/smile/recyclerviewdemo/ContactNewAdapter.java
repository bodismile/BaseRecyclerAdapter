package com.smile.recyclerviewdemo;

import android.content.Context;
import android.view.View;

import com.smile.recyclerview.BaseRecyclerAdapter;
import com.smile.recyclerview.BaseRecyclerHolder;
import com.smile.recyclerview.IMutlipleItem;
import com.smile.recyclerviewdemo.bean.Contact;

import java.util.Collection;

/**替换ContactAdapter的一种简洁的实现方式，可用于多种Item布局的recycleView实现
 * @author :smile
 * @project:ContactNewAdapter
 * @date :2016-04-27-14:18
 */
public class ContactNewAdapter extends BaseRecyclerAdapter<Contact> {

    public ContactNewAdapter(Context context, IMutlipleItem<Contact> items, Collection<Contact> datas) {
        super(context,items,datas);
    }

    @Override
    public void bindView(BaseRecyclerHolder holder, Contact contact, int position) {
        if(holder.layoutId==R.layout.item_contact){
            holder.setImageView(contact == null ? null : contact.getAvator(), R.mipmap.head, R.id.iv_recent_avatar);
            holder.setText(R.id.tv_recent_name,contact==null?"未知":contact.getUsername());
        }else if(holder.layoutId==R.layout.header_new_friend){
            holder.setVisible(R.id.iv_msg_tips, View.VISIBLE);
        }else if(holder.layoutId==R.layout.header_group){
            holder.setVisible(R.id.iv_msg_tips, View.GONE);
        }
    }

}
