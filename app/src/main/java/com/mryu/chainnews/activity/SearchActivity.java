package com.mryu.chainnews.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import com.cdye.baselibrary.BaseActivity;
import com.mryu.chainnews.R;
import com.mryu.chainnews.adapter.SearchHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyanqing
 * @内容 搜索接口类
 * @date 2018/10/19
 * @describe TODO
 */
 
public class SearchActivity extends BaseActivity {
	//适配器三个 一个数据的Input 一个textview 一个Recyclerview
	//RecyclerView并不会完全替代ListView
	//（这点从ListView没有被标记为@Deprecated可以看出），两者的使用场景不一样。
	//但是RecyclerView的出现会让很多开源项目被废弃
	//例如横向滚动的ListView, 横向滚动的GridView, 瀑布流控件，因为RecyclerView能够实现所有这些功能。

    //首先五个适配器需要注册 input 搜索框 search 输入框输入 一个view 一个搜索历史 一个沉浸式导航Toobar
    private EditText input;
    private TextView search;
    private RecyclerView recyclerView;

    private SearchHistoryAdapter adapter;
    private Toolbar toolbar1;
    @Override
	//得到Id
    protected int getLayoutId() {
        return R.layout.activity_search;
    }
    //find找到几个id 并且添加适配器
    //toolbar头部
    //input文字搜索框
    //search搜索那俩个字
    @Override
    protected void init(Bundle bundle) {
        toolbar1=findViewById(R.id.toolbar1);
        mImmersionBar.titleBar(toolbar1).init();
        input=findViewById(R.id.input);
        search=findViewById(R.id.search);
        recyclerView=findViewById(R.id.recycler_view);
        //适配器
        adapter=new SearchHistoryAdapter();
        //onclick事件
        adapter.setOnItemClickListener(new SearchHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String text) {
                startSearch(text);
            }
        });
        //recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        List<String> list=new ArrayList<>();
        //设想for循环但是意义不大所以为了测试添加一条数据
        list.add("yuyanqing此功能程序员正在探索中");
        adapter.setData(list);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到list集合的字并展示
               String name= input.getText().toString();
               if (!TextUtils.isEmpty(name)){
                   startSearch(name);
               }

            }
        });

    }

    private void startSearch(String name){

        hintKeyBoard();
        finish();
    }
    public void hintKeyBoard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


}
