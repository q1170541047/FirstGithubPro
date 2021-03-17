package com.yiche.basemoudle;

import com.yiche.baselibrary.javabean.base.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ICommomApiService {
    @GET(ApiConstants.VERSION_UPDATE)
    Observable<BaseBean> version_update();

}
