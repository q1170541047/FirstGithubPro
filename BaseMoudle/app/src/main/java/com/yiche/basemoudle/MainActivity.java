package com.yiche.basemoudle;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import android.os.Bundle;

import com.yiche.baselibrary.netWork.CustomTransformer;
import com.yiche.baselibrary.util.LogUtil;
import com.yiche.baselibrary.util.MyToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Disposable a = HttpUtil.getInstance().createService(ICommomApiService.class)
                .version_update()
                .compose(new CustomTransformer<>())
                .doOnSubscribe(disposable -> {
//                        getContext().showLoadingDialog();
                }).subscribe(baseBean -> {
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
//                AppUtil.showException(throwable);
                        LogUtil.d(throwable.getMessage());
                        MyToastUtil.showToast(throwable.getMessage());
                    }
                });
    }
}
