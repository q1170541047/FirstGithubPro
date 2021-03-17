package com.yiche.baselibrary.netWork;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * 转换器
 */
public class CustomTransformer<T> implements ObservableTransformer<T, T> {
    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}