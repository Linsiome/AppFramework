package com.aolan.b365.mvp.ui.fragment;

import com.aolan.b365.R;
import com.aolan.b365.base.BaseFragment;
import com.aolan.b365.base.IBaseView;
import com.aolan.b365.mvp.presenter.ClassifyPresenter;
import com.aolan.b365.mvp.presenter.PersonalPresenter;

public class PersonalFragment extends BaseFragment<PersonalPresenter> implements IBaseView {
    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initEventAndData() {

    }
}
