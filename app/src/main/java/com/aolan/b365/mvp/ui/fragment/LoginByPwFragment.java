package com.aolan.b365.mvp.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.aolan.b365.R;
import com.aolan.b365.base.BaseFragment;
import com.aolan.b365.base.IBaseView;
import com.aolan.b365.manager.UserManager;
import com.aolan.b365.mvp.contract.LoginContract;
import com.aolan.b365.mvp.model.entity.LoginInfo;
import com.aolan.b365.mvp.presenter.LoginPresenter;
import com.aolan.b365.mvp.ui.activity.MainActivity;
import com.aolan.b365.utils.CacheActivity;
import com.aolan.b365.utils.LogUtil;
import com.aolan.b365.utils.PhoneFormatCheckUtils;
import com.aolan.b365.widget.OnekeyEditTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginByPwFragment extends BaseFragment<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_phone_number)
    OnekeyEditTextView etPhoneNumber;
    @BindView(R.id.et_password)
    OnekeyEditTextView etPassword;
    @BindView(R.id.btn_login_by_pd)
    Button btnLoginByPW;
    @BindView(R.id.tv_error_tips)
    TextView textViewError;
    private String phone;
    private String password;
    Animation anim;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_by_pw;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }

    public void init() {
        String phoneNumber = UserManager.getInstance().getLoginPhone();
        if (!phoneNumber.equals(null)) {
            etPhoneNumber.setText(phoneNumber);
            phone = phoneNumber;
        }

        anim = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
    }

    @OnClick({R.id.btn_login_by_pd, R.id.tv_register, R.id.tv_forget_password})
    public void onLoginClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_by_pd://登录
//                phone = etPhoneNumber.getEtContent().getText().toString().trim();
//                password = etPassword.getEtContent().getText().toString().trim();
//                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
//                    setErrorTips(getString(R.string.login_phone_password_null_tips));
//                    etPhoneNumber.requestFocus();
//                    return;
//                } else if (phone.length() < 11 || !PhoneFormatCheckUtils.matchesPhoneNumber(phone)) {
//                    setErrorTips(getString(R.string.phone_error_tips));
//                    etPhoneNumber.requestFocus();
//                    return;
//                } else if (!PhoneFormatCheckUtils.matchesPassword(password)) {
//                    setErrorTips(getString(R.string.password_format_error_tips));
//                    etPassword.requestFocus();
//                    return;
//                }

                phone = "";
                password = "";
                mPresenter.loginByPw(phone,password);
                break;
//            case R.id.tv_register://立即注册
//                if (!CacheActivity.activityList.contains(getActivity())) {
//                    CacheActivity.addActivity(getActivity());
//                }
//                RegisterActivity.open(getContext());
//                break;
//            case R.id.tv_forget_password://忘记密码
//                SetPasswordVerifyActivity.open(getContext());
//                break;
        }

    }

    @Override
    public void loginSuccess(LoginInfo loginInfo) {
        UserManager.getInstance().setUserToken(loginInfo.getData().getAccess_token());
        UserManager.getInstance().setRefreshToken(loginInfo.getData().getRefresh_token());
        UserManager.getInstance().setLoginPhone(phone);
        MainActivity.openFinishSource(getContext());
    }

    @Override
    public void setErrorTips(String tips) {
        textViewError.setVisibility(View.VISIBLE);
        textViewError.setText(tips);
        textViewError.startAnimation(anim);
    }

}
