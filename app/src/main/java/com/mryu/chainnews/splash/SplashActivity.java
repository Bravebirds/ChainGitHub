package com.mryu.chainnews.splash;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mryu.chainnews.MainActivity;
import com.mryu.chainnews.R;
/**
 * @author yuyanqing
 * @内容 Splash引导页
 * @date 2018/9/19
 * @describe TODO
 */
public class SplashActivity extends AppCompatActivity {
    //自定义一个ACTIVITY_TAG打印线程到控制台
    protected static final String ACTIVITY_TAG="Log";
    @Override
    //Bundle线程
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里创建第一个activity_splash
        setContentView(R.layout.activity_splash);
        //Handler线程倒计时
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //首先执行SplashActivity然后进入MainActivity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                //ProductTourActicity方法调用
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						finish();
					}
				}, 300);
            }
        }, 500);
    }
}
