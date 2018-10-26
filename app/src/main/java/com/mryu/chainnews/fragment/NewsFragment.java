package com.mryu.chainnews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cdye.baselibrary.BaseFragment;
import com.cdye.http.HttpRequestProxy;
import com.cdye.http.ICallBack;
import com.google.gson.Gson;
import com.mryu.chainnews.R;
import com.mryu.chainnews.activity.WebActivity;
import com.mryu.chainnews.adapter.NewsAdapter;
import com.mryu.chainnews.entity.Data;
import com.mryu.chainnews.entity.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;

/**
 * @author yuyanqing
 * @内容 新闻节目的Fragment
 *@JSON 密码以及api解析
 * Created by Administrator on 2018/10/15 0015.
 */

public class NewsFragment extends BaseFragment {
    //适配器recyclerview性能优化
    private RecyclerView recyclerView;
    //刷新的功能Refresh
    SmartRefreshLayout refreshLayout;
    //新闻以及图片
    private NewsAdapter adapter;
    private ImageView noData;
    //api的网址这里我采用聚合数据
    String url="http://v.juhe.cn/toutiao/index";
    private String type;

    public NewsFragment() {
    }

    public NewsFragment setType(String type) {
        this.type = type;
        return this;
    }
    //返回值到id
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    //bunndle数据的传输
    protected void initView(View view, Bundle bundle) {
        //一个recycler_view展示新闻列表
        recyclerView=view.findViewById(R.id.recycler_view);
        //一个refresh_layout刷新
        refreshLayout=view.findViewById(R.id.refresh_layout);
        //一个no_data没有数据的时候展示一张图片并请刷新数据
        noData=view.findViewById(R.id.no_data);
        //new一个adapter
        adapter=new NewsAdapter();
        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Data data) {
                Intent intent=new Intent(_mActivity, WebActivity.class);
                //intent网址然后得到这个网址数据
                intent.putExtra("url",data.getUrl());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(_mActivity, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            //刷新数据
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getNews();
            }
        });
        noData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLayout.autoRefresh();
                noData.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getNews();
    }

    private void getNews(){
        //HashMap密钥的方法封装键值对相当于list集合
        HashMap<String,String> map=new HashMap();
        map.put("key","30e5da0b397094396ea60f0136ecc516");
        map.put("type",type);
        HttpRequestProxy.getInstance().get(url, map, new ICallBack() {
            @Override
            public void onSuccess(final String s) {
                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Response response=new Gson().fromJson(s, Response.class);
                        if (response!=null&&response.getResult()!=null&&response.getResult().getData()!=null){
                            noData.setVisibility(View.GONE);
                            adapter.setDatas(response.getResult().getData());
                        }else {
                            if (response!=null){
                                //控制台输出一个日志
                                Toast.makeText(_mActivity,response.getReason(),Toast.LENGTH_SHORT).show();
                            }
                            //没有数据的时候展示一张指定图片
                            noData.setVisibility(View.VISIBLE);
                        }
                        //刷新
                        refreshLayout.finishRefresh();
                    }
                });

            }
            //Throwable是刷新请求错误，相当于Exception

            @Override
            public void onFailure(Throwable throwable) {
                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //完成刷新操作
                        noData.setVisibility(View.VISIBLE);
                        refreshLayout.finishRefresh();
                    }
                });

            }
        });

    }
}
