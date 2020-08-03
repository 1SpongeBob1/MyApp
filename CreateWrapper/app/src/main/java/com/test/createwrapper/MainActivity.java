package com.test.createwrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.anythink.banner.api.ATBannerListener;
import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.ATSDK;
import com.anythink.core.api.AdError;
import com.anythink.interstitial.api.ATInterstitialListener;
import com.anythink.rewardvideo.api.ATRewardVideoListener;
import com.test.createwrapper.databinding.ActivityMainBinding;
import com.test.createwrapper.wapper.ATBannerViewWrapper;
import com.test.createwrapper.wapper.ATInterstitialWrapper;
import com.test.createwrapper.wapper.ATRewardVideoAdWrapper;

/**
 * 广告测试ID文档链接：https://docs.toponad.com/#/zh-cn/android/android_doc/android_access_doc
 */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Android 9及以上必须设置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = Application.getProcessName();
            if (!getPackageName().equals(processName)) {
                WebView.setDataDirectorySuffix(processName);
            }
        }


        ATSDK.integrationChecking(this);
        ATSDK.init(getApplicationContext(), "a5aa1f9deda26d", "4f7b9ac17decb9babec83aac078742c7");
        ATSDK.setNetworkLogDebug(true);

        float ratio = 320 / 50f;//此比例需与TopOn后台Banner的比例相同
        width = getResources().getDisplayMetrics().widthPixels;//定一个宽度值，比如屏幕宽度
        height = (int) (width / ratio);
        binding.bannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ATBannerViewWrapper wrapper = new ATBannerViewWrapper(MainActivity.this);
                wrapper.setUnitID("b5c0508c4c073f");
                wrapper.setSize(width, height);
                wrapper.setView(MainActivity.this);
                wrapper.setListener(new ATBannerListener() {
                    @Override
                    public void onBannerLoaded() {
                        Log.d("test", "onLoad");
                    }

                    @Override
                    public void onBannerFailed(AdError adError) {
                        adError.printStackTrace();
                        Log.d("test", "onFailed");
                    }

                    @Override
                    public void onBannerClicked(ATAdInfo atAdInfo) {
                        Log.d("test", "onClicked");
                    }

                    @Override
                    public void onBannerShow(ATAdInfo atAdInfo) {
                        Log.d("test", "onShow");
                    }

                    @Override
                    public void onBannerClose(ATAdInfo atAdInfo) {
                        Log.d("test", "onClose");
                    }

                    @Override
                    public void onBannerAutoRefreshed(ATAdInfo atAdInfo) {
                        Log.d("test", "onRefreshed");
                    }

                    @Override
                    public void onBannerAutoRefreshFail(AdError adError) {
                        Log.d("test", "onRefreshedFailed");
                    }
                });
                Log.d("test", "-----------");
                wrapper.loadAd();
            }
        });

        binding.interstitialAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ATInterstitialWrapper wrapper = new ATInterstitialWrapper(MainActivity.this, "b5c0508e2c84d4");
                wrapper.setListener(new ATInterstitialListener() {
                    @Override
                    public void onInterstitialAdLoaded() {

                    }

                    @Override
                    public void onInterstitialAdLoadFail(AdError adError) {

                    }

                    @Override
                    public void onInterstitialAdClicked(ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onInterstitialAdShow(ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onInterstitialAdClose(ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onInterstitialAdVideoStart(ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onInterstitialAdVideoError(AdError adError) {

                    }
                });
                wrapper.isReady();
                wrapper.load();
                wrapper.show();
            }
        });

        binding.rewardAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ATRewardVideoAdWrapper wrapper = new ATRewardVideoAdWrapper(MainActivity.this, "b5c2c800fb3a52");
                wrapper.setListener(new ATRewardVideoListener() {
                    @Override
                    public void onRewardedVideoAdLoaded() {

                    }

                    @Override
                    public void onRewardedVideoAdFailed(AdError adError) {

                    }

                    @Override
                    public void onRewardedVideoAdPlayStart(ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onRewardedVideoAdPlayEnd(ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onRewardedVideoAdPlayFailed(AdError adError, ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onRewardedVideoAdClosed(ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onRewardedVideoAdPlayClicked(ATAdInfo atAdInfo) {

                    }

                    @Override
                    public void onReward(ATAdInfo atAdInfo) {

                    }
                });
                wrapper.isReady();
                wrapper.load();
                wrapper.show();
            }
        });
    }
}
