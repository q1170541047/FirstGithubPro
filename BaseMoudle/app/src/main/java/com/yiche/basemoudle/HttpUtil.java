package com.yiche.basemoudle;

import com.yiche.baselibrary.app.BaseApplicaticon;
import com.yiche.baselibrary.netWork.CustomConverterFactory;
import com.yiche.baselibrary.netWork.MyHttpLoggingInterceptor;
import com.yiche.baselibrary.util.pro.AppUtil;
import com.yiche.baselibrary.util.pro.UserManager;
import com.yiche.baselibrary.util.pro.VisionUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 *
 */
public class HttpUtil {

    private Retrofit mRetrofit;

    private HttpUtil() {
        mRetrofit = new Retrofit.Builder()
                .client(initClient())
                .baseUrl("https://api-dev.yicheshuke.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CustomConverterFactory.create())
                .build();
    }

    public static HttpUtil getInstance() {
        return SingletonHolder.HTTP_UTIL;
    }

    public <T> T createService(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    private OkHttpClient initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        NetInterceptor netInterceptor = new NetInterceptor();
        builder.addInterceptor(netInterceptor);
//        NetInterceptorResult netInterceptorResult = new NetInterceptorResult();
//        builder.addInterceptor(netInterceptorResult);
//        builder.addInterceptor(new AddCookiesInterceptor());
//        builder.addInterceptor(new ReceivedCookiesInterceptor());
        MyHttpLoggingInterceptor interceptor = new MyHttpLoggingInterceptor(new HttpLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        encrypt(builder);
        return builder.build();
    }


    private void encrypt(OkHttpClient.Builder builder) {
        TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.hostnameVerifier((hostname, session) -> true);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private class NetInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
//            if (!AppUtil.isNetWorkAvailable()) {
//                Activity activity = ActivityManager.getInstance().currentActivity();
//                if (activity != null) {
//                    activity.runOnUiThread(() -> ToastUtil.showToast("网络连接不可用"));
//                }
//            }
            Request originalRequest = chain.request();
            HttpUrl oldUrl = originalRequest.url();
            String requestUrl = originalRequest.url().toString();
            Request.Builder builder = originalRequest.newBuilder();
            builder.addHeader("Authorization", UserManager.getInstance().getTokenString())
                    .addHeader("appToken", UserManager.getInstance().getTokenString())
                    .addHeader("system", "Android")
                    .addHeader("appVersion", VisionUtils.getAppVersionName(BaseApplicaticon.getMyInstance()))
                    .addHeader("versionWeigh", VisionUtils.getAppVersionCode(BaseApplicaticon.getMyInstance()))
                    .addHeader("channel", AppUtil.getChannelName(BaseApplicaticon.getMyInstance()));
            String baseUrl = "https://api-dev.yicheshuke.com/";
            HttpUrl baseURL = null;
            baseURL = HttpUrl.parse(baseUrl);
            HttpUrl newHttpUrl = oldUrl.newBuilder().scheme(baseURL.scheme()).host(baseURL.host()).port(baseURL.port()).build();
            //获取处理后的新newRequest            Request newRequest = builder.url(newHttpUrl).build();
            Request newRequest = builder.url(newHttpUrl).build();
            if (requestUrl.contains(ApiConstants.SUBMIT_QUERY)) {
                return chain.withReadTimeout(30, TimeUnit.SECONDS)
                        .withWriteTimeout(30, TimeUnit.SECONDS).proceed(newRequest);
            }
            return chain.proceed(newRequest);
        }
    }





    private static class SingletonHolder {
        private static HttpUtil HTTP_UTIL = new HttpUtil();
    }
}