package com.mryu.chainnews;

import android.app.Application;
import android.content.Context;

import com.cdye.http.HttpRequestProxy;
import com.cdye.http.request.OkhttpRequest;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

 /**
 * @author yuyanqing
 * @内容 全局设置主题颜色
 * @date 2018/10/19
 * @describe TODO
 */

public class ChainNewsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        HttpRequestProxy.init(new OkhttpRequest());
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new MaterialHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
    }
}
