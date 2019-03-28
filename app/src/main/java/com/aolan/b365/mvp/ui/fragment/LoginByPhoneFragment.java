package com.aolan.b365.mvp.ui.fragment;


import android.annotation.SuppressLint;
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
import com.aolan.b365.mvp.model.entity.LoginInfo;
import com.aolan.b365.mvp.presenter.LoginPresenter;
import com.aolan.b365.mvp.ui.activity.MainActivity;
import com.aolan.b365.netWork.NetClient;
import com.aolan.b365.utils.CacheActivity;
import com.aolan.b365.utils.LogUtil;
import com.aolan.b365.utils.PhoneFormatCheckUtils;
import com.aolan.b365.widget.OnekeyEditTextView;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginByPhoneFragment extends BaseFragment<LoginPresenter>  implements IBaseView {
    @BindView(R.id.et_phone_number)
    OnekeyEditTextView etPhoneNumber;

    @BindView(R.id.et_validate_code)
    OnekeyEditTextView etValidateCode;

    @BindView(R.id.btn_v_code)
    Button btnValidateCode;

    @BindView(R.id.btn_login_by_code)
    Button btnLoginByCode;

    @BindView(R.id.tv_error_tips)
    TextView textViewError;

    Animation anim;
    private String phone;
    private String code;

    private PublishSubject<Object> mStopCountingSubject;
    private Observable<Long> mCountDownObservable;
    private boolean mCountToZero;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_by_phone;
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

    @OnClick({R.id.btn_login_by_code, R.id.tv_register, R.id.tv_forget_password})
    public void onLoginClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_by_code://登录
                phone = etPhoneNumber.getEtContent().getText().toString().trim();
                code = etValidateCode.getEtContent().getText().toString().trim();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
                    setErrorTips(getString(R.string.login_phone_code_null_tips));
                    etPhoneNumber.requestFocus();
                    return;
                } else if (phone.length() < 11 || !PhoneFormatCheckUtils.matchesPhoneNumber(phone)) {
                    setErrorTips(getString(R.string.phone_error_tips));
                    etPhoneNumber.requestFocus();
                    return;
                } else if (code.length() < 4) {
                    setErrorTips(getString(R.string.code_format_error_tips));
                    etValidateCode.requestFocus();
                    return;
                }

                login();
                break;
//            case R.id.tv_register://立即注册
//                if (!CacheActivity.activityList.contains(getActivity())) {
//                    CacheActivity.addActivity(getActivity());
//                }
//                RegisterActivity.open(getContext());
//                break;
//            case R.id.tv_forget_password://忘记密码
//                //SetUserInfoStepOneActivity.open(getContext());
//                //SetUserInfoStepTwoActivity.open(getContext());
//                SetPasswordVerifyActivity.open(getContext());
//                break;
        }


    }

    @OnClick(R.id.btn_v_code)
    public void onGetCodeClick(View view) {
        phone = etPhoneNumber.getEtContent().getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            setErrorTips(getString(R.string.phone_null_tips));
            etPhoneNumber.requestFocus();
            return;
        } else if (!PhoneFormatCheckUtils.matchesPhoneNumber(phone)) {
            setErrorTips(getString(R.string.phone_error_tips));
            etPhoneNumber.requestFocus();
            return;
        }

        getCode();

    }

    private void setErrorTips(String tips) {
        textViewError.setVisibility(View.VISIBLE);
        textViewError.setText(tips);
        textViewError.startAnimation(anim);
    }

    /**
     * 验证码登录
     */
    private void login() {
//        NetClient.getInstance(getContext()).getNetApi3().login_code(phone, code)
//                .compose(this.<LoginInfo>dTSchedulers3())
//                .subscribe(new MSubscriber<LoginInfo>(getActivity()) {
//                    @Override
//                    public void onNext(LoginInfo baseModle) {
//                        int status_code = baseModle.getCode();
//                        if (status_code == 200) {
//                            UserManager.getInstance().setUserToken(baseModle.getData().getAccess_token());
//                            UserManager.getInstance().setLoginPhone(phone);
//                            MainActivity.openFinishSource(getContext());
//                        } else {
//                            setErrorTips(baseModle.getMessage());
//                        }
//                        LogUtil.d(baseModle.getStatus() + "===" + baseModle.getCode());
//                    }
//
//                });

    }


    /**
     * 获取验证码
     */

    private void getCode() {
//        NetClient.getInstance(getActivity()).getNetApi3().send_code(phone, "login")
//                .compose(cTSchedulers())
//                .subscribe(new MSubscriber<BaseModle>(getActivity()) {
//                    @Override
//                    public void onNext(BaseModle baseModle) {
//                        int status_code = baseModle.getCode();
//                        if (status_code == 200) {
//                            countTime();
//                            ToastFontUtil toastFontUtil = new ToastFontUtil(getContext(), R.layout.toast_center, getString(R.string.text_code_send_success));
//                            toastFontUtil.show(2000);
//                        } else {
//                            setErrorTips(baseModle.getStatus());
//                        }
//                        LogUtil.d(baseModle.getStatus() + "===" + baseModle.getCode());
//                    }
//
//                });
    }

    /**
     * 计时
     */
    private void countTime() {
//        mStopCountingSubject = PublishSubject.create();
//        mCountDownObservable.subscribe(new Subscriber<Long>() {
//
//            @Override
//            public void onStart() {
//                btnValidateCode.setEnabled(true);
//                btnValidateCode.setText(getString(R.string.text_get_code));
//            }
//
//            @Override
//            public void onCompleted() {
//                btnValidateCode.setEnabled(true);
//                btnValidateCode.setText(getString(R.string.text_get_code));
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                LogUtil.d("onError" + e.getMessage());
//            }
//
//            @SuppressLint("StringFormatMatches")
//            @Override
//            public void onNext(Long aLong) {
//                int countdown = 60 - 1 - aLong.intValue();
//                mCountToZero = (countdown == 0);
//                btnValidateCode.setEnabled(false);
//                btnValidateCode.setText(getString(R.string.text_get_code_after, countdown));
//            }
//        });
//        mCountDownObservable = Observable.interval(1, TimeUnit.SECONDS)
//                .take(60)
//                .takeUntil(mStopCountingSubject)
//                .observeOn(AndroidSchedulers.mainThread());
    }

}
