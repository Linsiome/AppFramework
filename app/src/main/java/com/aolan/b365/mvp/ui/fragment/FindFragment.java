package com.aolan.b365.mvp.ui.fragment;

import com.aolan.b365.R;
import com.aolan.b365.base.BaseFragment;
import com.aolan.b365.base.IBaseView;
import com.aolan.b365.mvp.presenter.ClassifyPresenter;
import com.aolan.b365.mvp.presenter.FindPresenter;

/**
 * 分类
 */
public class FindFragment extends BaseFragment<FindPresenter> implements IBaseView {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initEventAndData() {

    }
}
