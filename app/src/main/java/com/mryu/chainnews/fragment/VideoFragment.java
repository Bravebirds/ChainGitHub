package com.mryu.chainnews.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cdye.baselibrary.BaseFragment;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.mryu.chainnews.R;

/**
 *@内容 视频界面采用htmlh5首先分享到QQ利用其h5特性这里跟MusicFragment一样的
 * Created by Administrator on 2018/10/15 0015.
 */

public class VideoFragment extends BaseFragment {
    private String url="http://m.iqiyi.com";

   //private String url="https://www.baidu.com/";测试数据忽略
    private WebView webView;
    private static final String TAG = "MainActivity";
    protected AgentWeb mAgentWeb;
    private LinearLayout container;
    private long mPressedTime = 0;
    private Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view, Bundle bundle) {
        toolbar=view.findViewById(R.id.toolbar);
        mImmersionBar.titleBar(toolbar).init();
        container = view.findViewById(R.id.container);
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
                .go(getUrl());
    }


    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG,url);
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
