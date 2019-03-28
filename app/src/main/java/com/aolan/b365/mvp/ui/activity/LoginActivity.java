package com.aolan.b365.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aolan.b365.R;
import com.aolan.b365.base.SimpleActivity;
import com.aolan.b365.manager.UserManager;
import com.aolan.b365.mvp.ui.adapter.LoginFragmentAdapter;
import com.aolan.b365.mvp.ui.fragment.LoginByPhoneFragment;
import com.aolan.b365.mvp.ui.fragment.LoginByPwFragment;
import com.aolan.b365.utils.IntentUtil;
import com.aolan.b365.widget.BlackStyleBackTitleBar;
import com.aolan.b365.widget.NoScrollViewPager;
import com.aolan.b365.widget.ViewPagerScroller;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends SimpleActivity {
    @BindView(R.id.top_toolbar)
    BlackStyleBackTitleBar top_toolbar;
    @BindView(R.id.tv_change_login_way)
    TextView textViewChange;
    @BindView(R.id.loginsign_viewPager)
    NoScrollViewPager viewPager;

    private boolean pagerFlag = true;

    public static void openFinishSource(Context context) {
        IntentUtil.startActivity(context, LoginActivity.class);
        ((FragmentActivity) context).supportFinishAfterTransition();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new ViewPagerScroller(this).initViewPagerScroll(viewPager);
        LoginByPwFragment loginByPwFragment = new LoginByPwFragment();
        LoginByPhoneFragment loginByPhoneFragment = new LoginByPhoneFragment();
        List<Fragment> alFragment = new ArrayList<Fragment>();
        alFragment.add(loginByPwFragment);
        alFragment.add(loginByPhoneFragment);
        //ViewPager设置适配器
        viewPager.setAdapter(new LoginFragmentAdapter(getSupportFragmentManager(), alFragment));
        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(0);
        //ViewPager页面切换监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public static void open(Context context, boolean tips) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("tips", tips);
        context.startActivity(intent);
        UserManager.getInstance().LoginOut();
        ((FragmentActivity) context).supportFinishAfterTransition();
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
