package com.smile.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smile.recyclerview.IMutlipleItem;
import com.smile.recyclerview.OnRecyclerViewListener;
import com.smile.recyclerviewdemo.bean.Contact;
import com.smile.recyclerviewdemo.bean.Message;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 演示创建多重布局的RecyclerView
 * @author smile
 */
public class ChatActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.ll_main)
    LinearLayout ll_main;
    @Bind(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @Bind(R.id.rc_view)
    RecyclerView rc_view;

    ChatAdapter adapter;
    private final int TYPE_TXT=0;
    private final int TYPE_VOICE=1;
    private final int TYPE_OTHER=2;

    Contact contact;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        contact =(Contact)getIntent().getSerializableExtra("contact");
        tv_title.setText(contact.getUsername());
        setView();
    }


    private void setView(){
        IMutlipleItem<Message> mutlipleItem = new IMutlipleItem<Message>() {

            @Override
            public int getItemViewType(int postion, Message msg) {
                if(msg.getType().equals("txt")){
                    return TYPE_TXT;
                }else if(msg.getType().equals("voice")){
                    return TYPE_VOICE;
                }else{
                    return TYPE_OTHER;
                }
            }

            @Override
            public int getItemLayoutId(int viewtype) {
                if(viewtype==TYPE_TXT){
                    return R.layout.item_text;
                }else if(viewtype==TYPE_VOICE){
                    return R.layout.item_voice;
                }else{
                    return R.layout.item_other;
                }
            }

            @Override
            public int getItemCount(List<Message> list) {
                return list.size();
            }
        };

        adapter = new ChatAdapter(this,mutlipleItem,null);
        rc_view.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rc_view.setLayoutManager(layoutManager);
        sw_refresh.setEnabled(true);
        setListener();
    }

    private void setListener(){
        ll_main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_main.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                sw_refresh.setRefreshing(true);
                query();
            }
        });
        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
            }
        });
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Log.i("smile", "点击的是：" + position);
            }

            @Override
            public boolean onItemLongClick(final int position) {
                Log.i("smile", "长按的是：" + position);
                return true;
            }
        });
    }

    private void query(){
        //模拟网络请求
        android.os.Message message = new android.os.Message();
        mHandler.sendMessageDelayed(message, 500);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            List<Message> lists =new ArrayList<>();
            Message c =new Message();
            c.setType("txt");
            c.setContent("这是一条文本消息");
            lists.add(c);
            Message c1 =new Message();
            c1.setType("voice");
            c1.setContent("这是一条语音消息");
            lists.add(c1);
            Message c2 =new Message();
            c2.setType("other");
            c2.setContent("这是一条其他消息");
            lists.add(c2);
            adapter.bindDatas(lists);
            sw_refresh.setRefreshing(false);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
