package com.yiche.camerax.video;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ScreenUtils;
import com.google.common.util.concurrent.ListenableFuture;
import com.gyf.immersionbar.ImmersionBar;
import com.yiche.camerax.R;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.extensions.AutoImageCaptureExtender;
import androidx.camera.extensions.AutoPreviewExtender;
import androidx.camera.extensions.BeautyImageCaptureExtender;
import androidx.camera.extensions.BeautyPreviewExtender;
import androidx.camera.extensions.BokehImageCaptureExtender;
import androidx.camera.extensions.BokehPreviewExtender;
import androidx.camera.extensions.HdrImageCaptureExtender;
import androidx.camera.extensions.HdrPreviewExtender;
import androidx.camera.extensions.NightImageCaptureExtender;
import androidx.camera.extensions.NightPreviewExtender;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CameraXRecordActivity extends AppCompatActivity {
    private static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
    private static final int PERMISSIONS_REQUEST_CODE = 10;
    private static final double RATIO_4_3_VALUE = 4.0 / 3.0;
    private static final double RATIO_16_9_VALUE = 16.0 / 9.0;
    private Size resolution = new Size(1080, 1920);
    private ArrayList<String> deniedPermission = new ArrayList<>();
    private String TAG = this.getClass().getSimpleName();
    private String outputFilePath;
    private PreviewView mPreviewView;
    private ImageView ivChange;
    private Preview mPreview;
    private ExecutorService mExecutorService;
    /**
     * ??????
     */
    private ImageCapture mImageCapture;
    /**
     * ????????????
     */
    private VideoCapture mVideoCapture;
    private ImageAnalysis mImageAnalysis;
    private Camera mCamera;
    /**
     * ???????????????camera????????????LifecycleOwner????????????????????????
     */
    private ProcessCameraProvider mCameraProvider;
    /**
     * ??????????????? ????????????
     */
    private int mLensFacing = CameraSelector.LENS_FACING_BACK;
    /**
     * ???????????????
     */
    private boolean takingPicture;

    public static boolean hsaPermission(Context context) {
        for (String permission : PERMISSIONS) {
            boolean res = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
            if (res) {
                return false;
            }
        }
        return true;
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, CameraXRecordActivity.class);
        activity.startActivity(intent);
    }

    public static void startVideo(Activity activity) {
        Intent intent = new Intent(activity, CameraXRecordActivity.class);
        intent.putExtra("type", 1);
        activity.startActivity(intent);
    }

    ImageView ivClose;
    ImageView ivPhoto;
    TextView ivFinsh;
    Chronometer textChrono;
    private boolean mIsRecording = false;
    public static final String INTENT_EXTRA_VIDEO_PATH = "intent_extra_video_path";//?????????????????????

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camerax_record);
        ImmersionBar mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        mExecutorService = Executors.newSingleThreadExecutor();
        if (!hsaPermission(this)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSIONS_REQUEST_CODE);
        } else {
            setUpCamera();
        }
        mPreviewView = findViewById(R.id.view_finder);
        ivChange = findViewById(R.id.ivChange);
        ivClose = findViewById(R.id.ivClose);
        ivFinsh = findViewById(R.id.ivFinsh);
        ivPhoto = findViewById(R.id.ivPhoto);
        textChrono = findViewById(R.id.textChrono);
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                if (mIsRecording) {
                    mVideoCapture.stopRecording();
                } else {

                    mIsRecording = true;
                    //??????
                    takingPicture = false;
                    //?????????????????????????????????
                    File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(),
                            System.currentTimeMillis() + ".mp4");
                    VideoCapture.Metadata metadata = new VideoCapture.Metadata();
                    VideoCapture.OutputFileOptions outputFileOptions = new VideoCapture.OutputFileOptions.Builder(file).build();
                    mVideoCapture.startRecording(outputFileOptions, Executors.newSingleThreadExecutor(), new VideoCapture.OnVideoSavedCallback() {

                        @Override
                        public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {
                            outputFilePath = file.getAbsolutePath();
                            onFileSaved(Uri.fromFile(file));
                            stopChronometer();
//                        MyToastUtil.showToast(outputFilePath);
                        }

                        @Override
                        public void onError(int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
                            Log.i(TAG, message);
                        }
                    });
                    ivChange.setEnabled(false);
                    ivPhoto.setImageResource(R.mipmap.player_stop);
                    startChronometer();
                }
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        updateCameraUi();
        ivChange.setOnClickListener(v -> {
            if (CameraSelector.LENS_FACING_FRONT == mLensFacing) {
                mLensFacing = CameraSelector.LENS_FACING_BACK;
            } else {
                mLensFacing = CameraSelector.LENS_FACING_FRONT;
            }
            bindCameraUseCases();
        });
    }

    private void stopChronometer() {
        textChrono.stop();
        textChrono.setVisibility(View.INVISIBLE);
    }

    //?????????
    private void startChronometer() {
        textChrono.setVisibility(View.VISIBLE);
        final long startTime = SystemClock.elapsedRealtime();
        textChrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer arg0) {
                long countUp = (SystemClock.elapsedRealtime() - startTime) / 1000;
                if (countUp % 2 == 0) {
                    // chronoRecordingImage.setVisibility(View.VISIBLE);
                } else {
                    //  chronoRecordingImage.setVisibility(View.INVISIBLE);
                }
                String asText = String.format("%02d", countUp / 60) + ":" + String.format("%02d", countUp % 60);
                textChrono.setText(asText);
            }
        });
        textChrono.start();
    }


    public int getmax() {

        if (!TextUtils.isEmpty(getIntent().getStringExtra("max"))) {

            return Integer.valueOf(getIntent().getStringExtra("max"));
        } else {
            return 100;

        }


    }

    @SuppressLint("RestrictedApi")
    private void onFileSaved(Uri savedUri) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            sendBroadcast(new Intent(android.hardware.Camera.ACTION_NEW_PICTURE, savedUri));
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap
                .getFileExtensionFromUrl(savedUri.getPath()));
        MediaScannerConnection.scanFile(getApplicationContext(),
                new String[]{new File(savedUri.getPath()).getAbsolutePath()},
                new String[]{mimeTypeFromExtension}, new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.d(TAG, "Image capture scanned into media store: $uri" + uri);
                    }
                });
        if (mIsRecording) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    stopChronometer();
                    mIsRecording = false;
                    ivChange.setEnabled(true);
                    ivPhoto.setImageResource(R.mipmap.camera_button);
                    Intent intent = new Intent();
                    intent.putExtra(INTENT_EXTRA_VIDEO_PATH, outputFilePath);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

        }
//        PreviewActivity.start(this, outputFilePath, !takingPicture);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            deniedPermission.clear();
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grant = grantResults[i];
                if (grant == PackageManager.PERMISSION_DENIED) {
                    deniedPermission.add(permission);
                }
            }
            if (deniedPermission.isEmpty()) {
                setUpCamera();
            } else {
                new AlertDialog.Builder(this)
                        .setMessage("????????????????????????????????????")
                        .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] denied = new String[deniedPermission.size()];
                                ActivityCompat.requestPermissions(CameraXRecordActivity.this, deniedPermission.toArray(denied), PERMISSIONS_REQUEST_CODE);
                            }
                        }).create().show();
            }
        }
    }

    private void updateCameraUi() {
        //?????????remove???add????????????????????????????????????????????????
        ViewGroup parent = (ViewGroup) mPreviewView.getParent();
        parent.removeView(mPreviewView);
        parent.addView(mPreviewView, 0);
    }

    private void setUpCamera() {
        //Future??????????????????????????????ListenableFuture???????????????????????????????????????????????????????????????
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    mCameraProvider = cameraProviderFuture.get();
                    //????????????????????????
                    mLensFacing = getLensFacing();
                    if (mLensFacing == -1) {
                        Toast.makeText(getApplicationContext(), "??????????????????cameraId!,???????????????????????????????????????", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // ??????????????????????????????
                    bindCameraUseCases();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    @SuppressLint("RestrictedApi")
    private void bindCameraUseCases() {
        //????????????????????????
        mPreviewView.post(new Runnable() {
            @Override
            public void run() {

                DisplayMetrics displayMetrics = new DisplayMetrics();
                mPreviewView.getDisplay().getRealMetrics(displayMetrics);
                //???????????????
                int screenAspectRatio = aspectRatio(displayMetrics.widthPixels, displayMetrics.heightPixels);

                int rotation = mPreviewView.getDisplay().getRotation();

                if (mCameraProvider == null) {
                    Toast.makeText(getApplicationContext(), "?????????????????????", Toast.LENGTH_SHORT).show();
                    return;
                }

                ProcessCameraProvider cameraProvider = mCameraProvider;

                CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(mLensFacing).build();

                Preview.Builder pBuilder = new Preview.Builder();

                setPreviewExtender(pBuilder, cameraSelector);

                mPreview = pBuilder
                        //???????????????
                        .setTargetAspectRatio(screenAspectRatio)
                        //???????????????????????????
                        .setTargetRotation(rotation)
                        .build();

                ImageCapture.Builder builder = new ImageCapture.Builder();

                setImageCaptureExtender(builder, cameraSelector);

                mImageCapture = builder
                        //?????????????????????????????????????????????
                        .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        //???????????????
                        .setTargetAspectRatio(screenAspectRatio)
                        //???????????????????????????
                        .setTargetRotation(rotation)
                        .build();
                mVideoCapture = new VideoCapture.Builder()
                        //??????????????????
                        .setTargetRotation(rotation)
                        //???????????????
                        .setTargetAspectRatio(screenAspectRatio)
//                ?????????
//                .setTargetResolution(resolution)
                        //????????????  ????????????????????????
                        .setVideoFrameRate(25)
                        //bit???  ????????????????????????
                        .setBitRate(3 * 1024 * 1024)
                        .build();
                mImageAnalysis = new ImageAnalysis.Builder()
                        .setTargetAspectRatio(screenAspectRatio)
                        .setTargetRotation(rotation)
                        .build();
                mImageAnalysis.setAnalyzer(mExecutorService, new ImageAnalysis.Analyzer() {
                    @Override
                    public void analyze(@NonNull ImageProxy image) {

                    }
                });


                //???????????????????????????????????????
                cameraProvider.unbindAll();


                mCamera = cameraProvider.bindToLifecycle(CameraXRecordActivity.this,
                        cameraSelector, mPreview, mImageCapture, mVideoCapture);
                mPreview.setSurfaceProvider(mPreviewView.getSurfaceProvider());

            }
        });
    }

    /**
     * ????????????????????? ??????TextureView ???????????????????????????
     *
     * @param textureView
     */
    private void transformsTextureView(TextureView textureView) {
        Matrix matrix = new Matrix();
        int screenHeight = ScreenUtils.getScreenHeight();
        int screenWidth = ScreenUtils.getScreenWidth();
        if (mLensFacing == CameraSelector.LENS_FACING_FRONT) {
            matrix.postScale(-1, 1, 1f * screenWidth / 2, 1f * screenHeight / 2);
        } else {
            matrix.postScale(1, 1, 1f * screenWidth / 2, 1f * screenHeight / 2);
        }
        textureView.setTransform(matrix);
    }

    /**
     * ???????????????????????????
     *
     * @param builder
     * @param cameraSelector
     */
    private void setPreviewExtender(Preview.Builder builder, CameraSelector cameraSelector) {
        AutoPreviewExtender extender = AutoPreviewExtender.create(builder);
        if (extender.isExtensionAvailable(cameraSelector)) {
            extender.enableExtension(cameraSelector);
        }
        BokehPreviewExtender bokehPreviewExtender = BokehPreviewExtender.create(builder);
        if (bokehPreviewExtender.isExtensionAvailable(cameraSelector)) {
            bokehPreviewExtender.enableExtension(cameraSelector);
        }
        HdrPreviewExtender hdrPreviewExtender = HdrPreviewExtender.create(builder);
        if (hdrPreviewExtender.isExtensionAvailable(cameraSelector)) {
            hdrPreviewExtender.enableExtension(cameraSelector);
        }
        BeautyPreviewExtender beautyPreviewExtender = BeautyPreviewExtender.create(builder);
        if (beautyPreviewExtender.isExtensionAvailable(cameraSelector)) {
            beautyPreviewExtender.enableExtension(cameraSelector);
        }
        NightPreviewExtender nightPreviewExtender = NightPreviewExtender.create(builder);
        if (nightPreviewExtender.isExtensionAvailable(cameraSelector)) {
            nightPreviewExtender.enableExtension(cameraSelector);
        }
    }

    /**
     * ???????????????????????????
     *
     * @param builder
     * @param cameraSelector
     */
    private void setImageCaptureExtender(ImageCapture.Builder builder, CameraSelector cameraSelector) {
        AutoImageCaptureExtender autoImageCaptureExtender = AutoImageCaptureExtender.create(builder);
        if (autoImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            autoImageCaptureExtender.enableExtension(cameraSelector);
        }
        BokehImageCaptureExtender bokehImageCaptureExtender = BokehImageCaptureExtender.create(builder);
        if (bokehImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            bokehImageCaptureExtender.enableExtension(cameraSelector);
        }
        HdrImageCaptureExtender hdrImageCaptureExtender = HdrImageCaptureExtender.create(builder);
        if (hdrImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            hdrImageCaptureExtender.enableExtension(cameraSelector);
        }
        BeautyImageCaptureExtender beautyImageCaptureExtender = BeautyImageCaptureExtender.create(builder);
        if (beautyImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            beautyImageCaptureExtender.enableExtension(cameraSelector);
        }
        NightImageCaptureExtender nightImageCaptureExtender = NightImageCaptureExtender.create(builder);
        if (nightImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            nightImageCaptureExtender.enableExtension(cameraSelector);
        }
    }

    private int aspectRatio(int widthPixels, int heightPixels) {
        double previewRatio = (double) Math.max(widthPixels, heightPixels) / (double) Math.min(widthPixels, heightPixels);
        if (Math.abs(previewRatio - RATIO_4_3_VALUE) <= Math.abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3;
        }
        return AspectRatio.RATIO_16_9;
    }

    private int getLensFacing() {
        if (hasBackCamera()) {
            return CameraSelector.LENS_FACING_BACK;
        }
        if (hasFrontCamera()) {
            return CameraSelector.LENS_FACING_FRONT;
        }
        return -1;
    }

    /**
     * ?????????????????????
     */
    private boolean hasBackCamera() {
        if (mCameraProvider == null) {
            return false;
        }
        try {
            return mCameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA);
        } catch (CameraInfoUnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ?????????????????????
     */
    private boolean hasFrontCamera() {
        if (mCameraProvider == null) {
            return false;
        }
        try {
            return mCameraProvider.hasCamera(CameraSelector.DEFAULT_BACK_CAMERA);
        } catch (CameraInfoUnavailableException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
