package com.mryu.chainnews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cdye.baselibrary.BaseFragment;
import com.mryu.chainnews.R;
import com.mryu.chainnews.activity.SearchActivity;

import java.util.ArrayList;
import java.util.List;

 /**
 * @author yuyanqing
 * @内容 新闻节目的Fragment
 * @date 2018/10/19
 * @describe TODO
 */

public class HomeFragment extends BaseFragment{
	//首先适配器创建
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private List<String> titles;
    private Toolbar toolbar;
    private FragmentPagerAdapter pagerAdapter;
    @Override
	//得到你所想找的布局名字
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    //JSON数据的解析传输
    @Override
    protected void initView(View view, Bundle bundle) {
        toolbar=view.findViewById(R.id.toolbar);
        mImmersionBar.titleBar(toolbar).init();
		//首先fragment_home  FindViewId 俩个控制的id一个tab的一个viewpager
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);
		//一个ArrayList集合添加上标签的头部
        titles=new ArrayList<>();
		//依次创建你所想要显示的文add创建
        //头条 社会 国内 国际 娱乐 体育 军事 科技 财经 时尚
        titles.add("头条");
        titles.add("社会");
        titles.add("国内");
        titles.add("国际");
        titles.add("娱乐");
        titles.add("体育");
        titles.add("军事");
        titles.add("科技");
        titles.add("财经");
        titles.add("时尚");
		//看到set这里当然是得到添加上面添加的对应得赋值
        //比如titles.add("头条")== tabLayout.addTab(tabLayout.newTab().setText("头条"));
        tabLayout.addTab(tabLayout.newTab().setText("头条"));
        tabLayout.addTab(tabLayout.newTab().setText("社会"));
        tabLayout.addTab(tabLayout.newTab().setText("国内"));
        tabLayout.addTab(tabLayout.newTab().setText("国际"));
        tabLayout.addTab(tabLayout.newTab().setText("娱乐"));
        tabLayout.addTab(tabLayout.newTab().setText("体育"));
        tabLayout.addTab(tabLayout.newTab().setText("军事"));
        tabLayout.addTab(tabLayout.newTab().setText("科技"));
        tabLayout.addTab(tabLayout.newTab().setText("财经"));
        tabLayout.addTab(tabLayout.newTab().setText("时尚"));
		//创建一个Json数据的集合
		//Fragment一个个的给值GSON比如头条就是fragments.add(new NewsFragment().setType("top"));
        fragments=new ArrayList<>();
        fragments.add(new NewsFragment().setType("top"));
        fragments.add(new NewsFragment().setType("shehui"));
        fragments.add(new NewsFragment().setType("guonei"));
        fragments.add(new NewsFragment().setType("guoji"));
        fragments.add(new NewsFragment().setType("yule"));
        fragments.add(new NewsFragment().setType("tiyu"));
        fragments.add(new NewsFragment().setType("junshi"));
        fragments.add(new NewsFragment().setType("keji"));
        fragments.add(new NewsFragment().setType("caijing"));
        fragments.add(new NewsFragment().setType("shishang"));
        pagerAdapter=new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            //Return返回值
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            //返回fragment长度9个字节
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            //return返回title也就是上边导航的数据比如:toutiao头条
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        };
        //适配器
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);//给Tabs设置适配器
        //find找到Home_fragment的id_search_layout实现onclick事件搜索框如果点击那么跳转SearchActivity
        view.findViewById(R.id.search_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(_mActivity, SearchActivity.class);
                startActivity(intent);
            }
        });

    }



}
