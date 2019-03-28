package com.aolan.b365.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aolan.b365.R;
import com.aolan.b365.base.BaseActivity;
import com.aolan.b365.mvp.contract.MainContract;
import com.aolan.b365.mvp.model.entity.TabEntity;
import com.aolan.b365.mvp.presenter.MainPresenter;
import com.aolan.b365.mvp.ui.adapter.LoginFragmentAdapter;
import com.aolan.b365.mvp.ui.fragment.ClassifyFragment;
import com.aolan.b365.mvp.ui.fragment.FindFragment;
import com.aolan.b365.mvp.ui.fragment.HomeFragment;
import com.aolan.b365.mvp.ui.fragment.LoginByPwFragment;
import com.aolan.b365.mvp.ui.fragment.PersonalFragment;
import com.aolan.b365.mvp.ui.fragment.ShoppingCartFragment;
import com.aolan.b365.utils.IntentUtil;
import com.aolan.b365.utils.LogUtil;
import com.aolan.b365.utils.ToastUitl;
import com.aolan.b365.widget.NoScrollViewPager;
import com.aolan.b365.widget.ViewPagerScroller;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;

    private String[] mTitles = {"首页", "分类", "发现", "购物车", "我"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_delete, R.mipmap.ic_delete, R.mipmap.ic_delete, R.mipmap.ic_delete, R.mipmap.ic_delete};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    List<Fragment> fragmentList;
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private FindFragment findFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private PersonalFragment personalFragment;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        initTab();
        mPresenter.getUserinfo();
    }


    @Override
    public void updateUI(String str) {

    }


    @Override
    public void useNightMode(boolean isNight) {

    }

    /**
     * 开启时，关闭来源
     *
     * @param context
     */
    public static void openFinishSource(Context context) {
        IntentUtil.startActivity(context, MainActivity.class);
        ((FragmentActivity) context).supportFinishAfterTransition();
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        fragmentList = new ArrayList<Fragment>();
        homeFragment = new HomeFragment();
        classifyFragment = new ClassifyFragment();
        findFragment = new FindFragment();
        shoppingCartFragment = new ShoppingCartFragment();
        personalFragment = new PersonalFragment();

        fragmentList.add(homeFragment);
        fragmentList.add(classifyFragment);
        fragmentList.add(findFragment);
        fragmentList.add(shoppingCartFragment);
        fragmentList.add(personalFragment);

        new ViewPagerScroller(this).initViewPagerScroll(viewPager);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);

        //tabLayout点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        //ViewPager设置适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        //为viewPager的滑动添加监听事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tabLayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(0);
        //预加载页面数量
        viewPager.setOffscreenPageLimit(fragmentList.size() - 1);
    }


    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }

        return false;
    }

    /**
     * 双击退出函数
     */
    private boolean isExit = false;
    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            ToastUitl.show("再按一次退出程序", Toast.LENGTH_SHORT);

            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }

}