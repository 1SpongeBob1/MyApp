package com.test.createwrapper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.anythink.banner.api.ATBannerListener;
import com.anythink.core.api.ATAdInfo;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ATBannerViewWrapper wrapper = new ATBannerViewWrapper(MainActivity.this);
                wrapper.setUnitID("b5baca41a2536f");
                wrapper.setListener(new ATBannerListener() {
                    @Override
                    public void onBannerLoaded() {
                        Log.d("test", "onLoad");
                    }

                    @Override
                    public void onBannerFailed(AdError adError) {
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
                ATInterstitialWrapper wrapper = new ATInterstitialWrapper(MainActivity.this, "b5baca54674522");
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
                ATRewardVideoAdWrapper wrapper = new ATRewardVideoAdWrapper(MainActivity.this, "b5b449f025ec7c");
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
