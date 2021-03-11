package com.yiche.ycysj.app.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.request.RequestOptions;

import com.yiche.ycysj.R;

import java.io.File;


/**
 * @author 1yqg
 *         crated at 2016/9/6
 * @name glide 图片加载
 * @description
 */
public class GlideUtils {

    private static GlideUtils instance;

    private GlideUtils() {
    }

    public static GlideUtils getGlideUtils() {
        if (instance == null) {
            instance = new GlideUtils();
        }
        return instance;
    }


    /**
     * 图片加载 glide
     *
     * @param context
     * @param imgUrl
     * @param defaultImgId
     * @param iv
     */
    public static void loadGlide(Context context, String imgUrl, int defaultImgId, final ImageView iv) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(defaultImgId);
        requestOptions.error(defaultImgId);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(imgUrl).apply(requestOptions).into(iv);

//        DrawableRequestBuilder requestBuilder = Glide.with(context)
//                .load(imgUrl)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE);//是将图片原尺寸缓存到本地
//        requestBuilder.error(defaultImgId);
////        if (defaultImgId != 0) {
////            requestBuilder.placeholder(defaultImgId);
////        }
//        requestBuilder.dontAnimate();
//        requestBuilder.into(iv);

        //.skipMemoryCache(true)//不缓存到内存中
        //.diskCacheStrategy(DiskCacheStrategy.NONE)//不硬盘缓存
        //.crossFade(R.anim.fade_out_rapidly, 5000)
        //pauseRequests()取消请求
        //resumeRequests()恢复请求
        //Glide.clear() 清除掉所有的图片加载请求
    }
    /**
     * 图片加载 glide ,无默认
     *
     * @param context
     * @param imgUrl
     * @param iv
     */
    public void loadGlide(Context context, String imgUrl, final ImageView iv) {


        Glide.with(context).load(imgUrl).into(iv);

//        DrawableRequestBuilder requestBuilder = Glide.with(context)
//                .load(imgUrl)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE);//是将图片原尺寸缓存到本地
//        requestBuilder.error(defaultImgId);
////        if (defaultImgId != 0) {
////            requestBuilder.placeholder(defaultImgId);
////        }
//        requestBuilder.dontAnimate();
//        requestBuilder.into(iv);

        //.skipMemoryCache(true)//不缓存到内存中
        //.diskCacheStrategy(DiskCacheStrategy.NONE)//不硬盘缓存
        //.crossFade(R.anim.fade_out_rapidly, 5000)
        //pauseRequests()取消请求
        //resumeRequests()恢复请求
        //Glide.clear() 清除掉所有的图片加载请求
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param resId
     * @param iv
     */
    public void loadGlide(Context context, int resId, final ImageView iv) {

        Glide.with(context)
                .load(resId)
                .into(iv);
    }

    public void loadGlideOfGif(Context context, int resId, final ImageView iv) {

   /*     Glide.with(context)
                .load(resId)
                .asGif()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(iv);*/
    }

    /**
     * 加载圆形图片
     */
    public void loadCircleTransform(Context context, String url, int defaultImgId, ImageView iv) {


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(defaultImgId);
        requestOptions.error(defaultImgId);
        requestOptions.transform(new GlideCircleTransform(context));
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(iv);
    }

    /**
     * 加载圆角的图片
     */
    public void loadRoundBitmap(Activity context, Integer resourceId, ImageView view, int dp) {
        RequestOptions myOptions = new RequestOptions()
                .centerCrop()
                .transform(new GlideRoundTransform(context, 10));

        Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .apply(myOptions)
                .into(view);
    }

    /**
     * 获取Glide造成的缓存大小
     *
     * @return CacheSize
     */
    public String getCacheSize(Context context) {
        try {
            return CacheUtil.getFormatSize(CacheUtil.getFolderSize(new File(context.getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取video压缩造成的缓存大小
     *
     * @return CacheSize
     */
    public String getVedioCacheSize(Context context) {
        try {
            Logger.i("getCacheSize", "lp_" + new File(context.getCacheDir() + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR));
            Logger.i("getCacheSize", "lp1_" + CacheUtil.getFormatSize(CacheUtil.getFolderSize(new File("/mnt/sdcard/videokit"))));
            Logger.i("getCacheSize", "lp2_" + CacheUtil.getFolderSize(new File("/mnt/sdcard/videokit")));
            return CacheUtil.getFormatSize(CacheUtil.getFolderSize(new File("/mnt/sdcard/videokit")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取app_video缓存大小
     *
     * @return CacheSize
     */
    public String getVedio2CacheSize(Context context) {
        try {
        /*    String appName = context.getPackageName();
            return CacheUtil.getFormatSize(CacheUtil.getFolderSize(new File(Environment.getExternalStorageDirectory() + "/" +appName)));*/
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), MediaFileUtils.SAVE_DIR_VIDEO);
            return CacheUtil.getFormatSize(CacheUtil.getFolderSize(mediaStorageDir));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取拍摄图片缓存大小
     *
     * @return CacheSize
     */
    public String getPhotoCacheSize(Context context) {
        try {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), MediaFileUtils.SAVE_DIR);
            return CacheUtil.getFormatSize(CacheUtil.getFolderSize(mediaStorageDir));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取拍摄图片缓存大小
     *
     * @return CacheSize
     */
    public String getPhotoSmallCacheSize(Context context) {
        try {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), IntentKeys.COMPRESS_PHOTO);
            return CacheUtil.getFormatSize(CacheUtil.getFolderSize(mediaStorageDir));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
