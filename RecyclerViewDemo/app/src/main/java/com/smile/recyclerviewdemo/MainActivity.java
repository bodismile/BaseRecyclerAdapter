package com.smile.recyclerviewdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smile.recyclerview.IMutlipleItem;
import com.smile.recyclerview.OnRecyclerViewListener;
import com.smile.recyclerviewdemo.bean.Contact;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 演示如何创建带header的RecycleView
 * @author smile
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.ll_main)
    LinearLayout ll_main;
    @Bind(R.id.sw_refresh)
    SwipeRefreshLayout sw_refresh;
    @Bind(R.id.rc_view)
    RecyclerView rc_view;

    ContactNewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setView();
    }

    private void setView(){
        //设置多重布局
        IMutlipleItem<Contact> mutlipleItem = new IMutlipleItem<Contact>() {

            @Override
            public int getItemViewType(int postion, Contact friend) {
                if(postion==0){//新朋友布局
                    return 0;
                }else if(postion==1){//群聊布局
                    return 1;
                }else{//联系人的list
                    return 2;
                }
            }

            @Override
            public int getItemLayoutId(int viewtype) {
                if(viewtype==0){
                    return R.layout.header_new_friend;
                }else if(viewtype==1){
                    return R.layout.header_group;
                }else{
                    return R.layout.item_contact;
                }
            }

            @Override
            public int getItemCount(List<Contact> list) {
                return list.size()+2;//增加两个自定义的头部布局
            }
        };
        //默认刚开始加载的时候,datas可为null
        adapter = new ContactNewAdapter(this,mutlipleItem,null);
        rc_view.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rc_view.setLayoutManager(layoutManager);
        sw_refresh.setEnabled(true);
        setListener();
    }

    private void setListener(){
        //自动下拉加载
        ll_main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ll_main.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                sw_refresh.setRefreshing(true);
                query();
            }
        });
        //下拉加载监听
        sw_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query();
            }
        });
        //设置点击/长按事件
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                toast("点击的是：" + position);
                if(position>1){
                    Intent intent =new Intent(MainActivity.this,ChatActivity.class);
                    intent.putExtra("contact", adapter.getItem(position));
                    startActivity(intent);
                }
            }

            @Override
            public boolean onItemLongClick(final int position) {
                toast("长按的是：" + position);
                if(position>1){
                    adapter.remove(position);
                }
                return true;
            }
        });
    }

    private void query(){
        //模拟网络请求
        Message msg = new Message();
        mHandler.sendMessageDelayed(msg, 500);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            List<Contact> lists =new ArrayList<>();
            Contact c =new Contact();
            c.setAvator("");
            c.setUsername("smile");
            lists.add(c);
            Contact c1 =new Contact();
            c1.setAvator("");
            c1.setUsername("smile1");
            lists.add(c1);
            adapter.bindDatas(lists);
            sw_refresh.setRefreshing(false);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

}
