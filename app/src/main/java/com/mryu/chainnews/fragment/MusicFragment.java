package com.mryu.chainnews.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.cdye.baselibrary.BaseFragment;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.mryu.chainnews.R;

/**
 *@内容 音乐界面web请求
 * Created by Administrator on 2018/10/15 0015.
 */

public class MusicFragment extends BaseFragment {
    //定义一个能用的网址方便于webview
    private String url="http://music.bbbbbb.me/";
    //封装用到了com.mryu.chiannews.activity.webactivity
    protected AgentWeb mAgentWeb;
    //LinearLayout封装
    private LinearLayout container;
    //请求的time时间默认0
    private long mPressedTime = 0;
    //上标题的沉浸式toolbar
    private Toolbar toolbar;

    @Override
    //get得到一个布局文件里面的id
    protected int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    //Bundle数据的传输
    protected void initView(View view, Bundle bundle) {
        toolbar=view.findViewById(R.id.toolbar);
        mImmersionBar.titleBar(toolbar).init();
        //找到id
        container = view.findViewById(R.id.container);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(container, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                //赋值得到布局
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                //请求网络地址
                .go(getUrl());

    }
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
        //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }
    };
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);

        }
    };

    @Override
    public void onPause() {

        super.onPause();

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mAgentWeb.getWebLifeCycle().onResume();
    }

    @Override
    public void onSupportInvisible() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onSupportInvisible();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("Info", "onResult:" + requestCode + " onResult:" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    public String getUrl() {
        return url;
    }
    //web返回事件监听
    @Override
    public boolean onBackPressedSupport() {
        if (mAgentWeb.back()){
            return true;
        }
        return super.onBackPressedSupport();
    }

}

