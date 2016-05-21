package com.smile.recyclerviewdemo;

import android.content.Context;
import android.view.View;

import com.smile.recyclerview.BaseRecyclerAdapter;
import com.smile.recyclerview.BaseRecyclerHolder;
import com.smile.recyclerview.IMutlipleItem;
import com.smile.recyclerviewdemo.bean.Contact;
import com.smile.recyclerviewdemo.bean.Message;

import java.util.Collection;

/**聊天的Adapter
 * @author :smile
 * @project:ChatAdapter
 * @date :2016-04-27-14:18
 */
public class ChatAdapter extends BaseRecyclerAdapter<Message> {

    public ChatAdapter(Context context, IMutlipleItem<Message> items, Collection<Message> datas) {
        super(context,items,datas);
    }

    @Override
    public void bindView(BaseRecyclerHolder holder, Message msg, int position) {
        if(holder.layoutId==R.layout.item_text){
            holder.setText(R.id.tv_recent_name,msg==null?"未知":msg.getContent()+","+msg.getType());
        }else if(holder.layoutId==R.layout.item_voice){
            holder.setText(R.id.tv_recent_name,msg==null?"未知":msg.getContent()+","+msg.getType());
        }else{
            holder.setText(R.id.tv_recent_name,msg==null?"未知":msg.getContent()+","+msg.getType());
        }
    }

}
