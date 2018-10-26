package com.mryu.chainnews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.content.Intent;
import com.cdye.baselibrary.BaseActivity;
import com.mryu.chainnews.fragment.HomeFragment;
import com.mryu.chainnews.fragment.MusicFragment;
import com.mryu.chainnews.fragment.SettingFragment;
import com.mryu.chainnews.fragment.VideoFragment;
import com.mryu.chainnews.splash.PrefConstants;
import com.mryu.chainnews.splash.ProductTourActivity;
import com.mryu.chainnews.splash.SAppUtil;

import java.util.ArrayList;
import java.util.List;

import static android.support.design.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED;
 /**
 * @author yuyanqing
 * @内容 主方法类
 * @date 2018/10/19
 * @describe TODO
 */
public class MainActivity extends BaseActivity {
    //默认自定义四个Fragment homeFragment, videoFragment, musicFragment, settingFragment
    private HomeFragment homeFragment;
    private VideoFragment videoFragment;
    private MusicFragment musicFragment;
    private SettingFragment settingFragment;
    //添加适配器
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragmentPagerAdapter pagerAdapter;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
     //BottomNavigationView一个封装方法
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
       //判断switch语句
	   //IF 选择其中一个那么现实其中的页面
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //switch循环语句判断
            switch (item.getItemId()) {
                //得到一个id return true返回为真
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_video:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_music:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_setting:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            //否则为假当然这里可以default默认值但是没必要
            //下面创建了一个方法设置默认值为0
            /**
             * public void onPageSelected(int position) {
             * if (menuItem != null) {
             * menuItem.setChecked(false);
             *} else {
             * navigation.getMenu().getItem(0).setChecked(false);
             * }
             * menuItem = navigation.getMenu().getItem(position);
             * menuItem.setChecked(true); }*/
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
	    //创建一个方法作为引导页checkShowTutorial
		checkShowTutorial();
        super.onCreate(savedInstanceState);

    }
	//checkShowTutorial方法具体
    // 做些什么?
	//需要继承
    //import com.mryu.chainnews.splash.PrefConstants;
    //import com.mryu.chainnews.splash.ProductTourActivity;
    //import com.mryu.chainnews.splash.SAppUtil;
	//主要做这几个事件
	//判断是否第一次使用进行引导页
    private void checkShowTutorial(){
        int oldVersionCode = PrefConstants.getAppPrefInt(this, "version_code");
        int currentVersionCode = SAppUtil.getAppVersionCode(this);
        if(currentVersionCode>oldVersionCode){
            //如果想进入mainactivity首先执行ProductTourActivity然后执行完后再进入主界面
            startActivity(new Intent(MainActivity.this,ProductTourActivity.class));
            //这里参考色差引导页
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            PrefConstants.putAppPrefInt(this, "version_code", currentVersionCode);
        }
    }
	//默认进入首页就选择的界面
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    //创建一个方法集合用于传输数据
    @Override
    protected void init(Bundle bundle) {
        //Fragment集合
        fragments=new ArrayList<>();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setLabelVisibilityMode(LABEL_VISIBILITY_LABELED);
        //创建适配器找到acticity_main_id_pager
        //new四个fragment
        viewPager=findViewById(R.id.view_pager);
        homeFragment=new HomeFragment();
        videoFragment=new VideoFragment();
        musicFragment=new MusicFragment();
        settingFragment=new SettingFragment();
		//添加你所需要的四个适配器
        fragments.add(homeFragment);
        fragments.add(videoFragment);
        fragments.add(musicFragment);
        fragments.add(settingFragment);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem = navigation.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(pagerAdapter);

    }
    private void replaceFragment(int id, Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(id,fragment).commitAllowingStateLoss();
    }

}
