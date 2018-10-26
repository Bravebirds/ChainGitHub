package com.mryu.chainnews.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.cdye.baselibrary.BaseActivity;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.mryu.chainnews.R;

/**
 * @author yuyanqing
 * @内容 web接口类
 * @date 2018/10/19
 * @describe TODO
 */
//这是一个web兼容的方法类
public class WebActivity extends BaseActivity {
    //AgentWeb是一个高度封装的androidWebView简单化支持文件上传以及下载简化javascript通信
    protected AgentWeb mAgentWeb;
    //这里的话是布局LinearLayout
    private LinearLayout container;
    //这里是一个网络接口的Url
    private String url;
    //首先return一个id activity_web
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }
    //Bundle数据之间的传输
    @Override
    protected void init(Bundle bundle) {
        //url也就是你指定的值这里find找到id.container
        url=getIntent().getStringExtra("url");
        container = findViewById(R.id.container);
        //高精度封装
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(container, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(url);
    }
    //WebViewClient主要辅助WebView处理Js的界面;例如一些图标以及网站title 加载速度等
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
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
//            if (toolbar!= null) {
//                toolbar.setTitle(title);
//            }

        }
    };
    //webback重写返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
