package com.aolan.b365.mvp.ui.fragment;

import com.aolan.b365.R;
import com.aolan.b365.base.BaseFragment;
import com.aolan.b365.base.IBaseView;
import com.aolan.b365.mvp.presenter.FindPresenter;
import com.aolan.b365.mvp.presenter.ShoppingCartPresenter;

/**
 * 分类
 */
public class ShoppingCartFragment extends BaseFragment<ShoppingCartPresenter> implements IBaseView {

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initEventAndData() {

    }
}
