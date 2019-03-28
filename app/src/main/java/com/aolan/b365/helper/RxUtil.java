package com.aolan.b365.helper;

import android.app.Dialog;
import android.content.DialogInterface;

import com.aolan.b365.base.BaseResponse;
import com.aolan.b365.base.IBaseView;
import com.aolan.b365.base.RxPresenter;
import com.aolan.b365.netWork.ApiException;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtil {

    //compose简化线程
    public static <T> ObservableTransformer<T, T> rxSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * compose简化线程,支持背压
     */
    public static <T> FlowableTransformer<T, T> rxFlowableSchedulers() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * rx转换器，直接返回BaseModle<T>里面的data
     * data为对象
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> aTSchedulers() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<BaseResponse<T>, T>() {
                    @Override
                    public T apply(BaseResponse<T> baseResponse) throws Exception {
                        if (baseResponse.getCode() == 200) {
                            return baseResponse.getData();
                        } else {
                            //ToastUtil.s(baseBean.errorMsg); 还是放到activity 和fragment 显示吧
                            throw Exceptions.propagate(new ApiException(baseResponse.getCode(), baseResponse.getMessage()));
                        }
                    }
                });
            }
        };
    }


    /**
     * rx转换器，直接返回BaseModle<T>里面的data
     * data为List
     */
    protected <T> ObservableTransformer<BaseResponse<List<T>>, List<T>> aListTSchedulers() {
        return new ObservableTransformer<BaseResponse<List<T>>, List<T>>() {
            @Override
            public ObservableSource<List<T>> apply(Observable<BaseResponse<List<T>>> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<BaseResponse<List<T>>, List<T>>() {
                    @Override
                    public List<T> apply(BaseResponse<List<T>> baseResponse) throws Exception {
                        if (baseResponse.getCode() == 200) {
                            return baseResponse.getData();
                        } else {
                            //Toast还是放到activity 和fragment 显示吧
                            throw Exceptions.propagate(new ApiException(baseResponse.getCode(), baseResponse.getMessage()));
                        }
                    }
                });
            }
        };
    }

    /**
     * 有进度Schedulers
     */
    public static <T> ObservableTransformer<T, T> dialogSchedulers(@NonNull IBaseView view,String msg) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull final Disposable disposable) throws Exception {
                                view.showProgressDialog(msg);//显示进度条
                            }
                        })
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                view.dimissProgressDialog();//隐藏进度条
                            }
                        });
            }
        };
    }


    public static <T> ObservableTransformer<T, T> applySchedulers(@NonNull IBaseView view,String msg) {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                view.showProgressDialog(msg);//显示进度条
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(new Action() {
                            @Override
                            public void run() {
                                view.dimissProgressDialog();//隐藏进度条
                            }
                        });
            }
        };
    }



    /**
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseResponse<T>, T> handleMyResult() {   //compose判断结果
        return new FlowableTransformer<BaseResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<BaseResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<BaseResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BaseResponse<T> baseResponse) {
                        if (baseResponse.getCode() == 200) {
                            return createData(baseResponse.getData());
                        } else {
                            return Flowable.error(new Exception());
                        }
                    }
                });
            }
        };
    }


    /**
     * 生成Flowable
     *
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

}
