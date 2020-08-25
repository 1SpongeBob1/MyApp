package com.test.addemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anythink.banner.api.ATBannerListener;
import com.anythink.banner.api.ATBannerView;
import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.ATSDK;
import com.anythink.core.api.AdError;
import com.anythink.interstitial.api.ATInterstitial;
import com.anythink.interstitial.api.ATInterstitialListener;
import com.anythink.nativead.api.ATNative;
import com.anythink.nativead.api.ATNativeAdRenderer;
import com.anythink.nativead.api.ATNativeAdView;
import com.anythink.nativead.api.ATNativeEventListener;
import com.anythink.nativead.api.ATNativeNetworkListener;
import com.anythink.nativead.api.NativeAd;
import com.anythink.nativead.unitgroup.a;
import com.anythink.rewardvideo.api.ATRewardVideoAd;
import com.anythink.rewardvideo.api.ATRewardVideoListener;

import java.util.HashMap;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {
    private Button back;
    private FrameLayout frameLayout;
    private Button bannerAd;
    private ATNative atNative;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ATSDK.integrationChecking(this);
        ATSDK.setNetworkLogDebug(true);//应用上线前必须关闭

        String appId = "a5aa1f9deda26d";
        String appKey = "4f7b9ac17decb9babec83aac078742c7";

        //初始化广告sdk
        ATSDK.init(this, appId, appKey);
        ATSDK.setNetworkLogDebug(true);

        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThirdActivity.this, SecondActivity.class));
            }
        });

        Button nativeAd = (Button) findViewById(R.id.nativeAd);
        nativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atNative = new ATNative(ThirdActivity.this, "b5c2c97629da0d", new ATNativeNetworkListener() {
                    @Override
                    public void onNativeAdLoaded() {
                        Log.v("nativeAd", "load success");
                        showNativeAd();
                    }

                    @Override
                    public void onNativeAdLoadFail(AdError adError) {
                        Log.v("nativeAd", "load failed");
                        adError.printStackTrace();
                    }
                });
                loadNativeAd();

            }
        });

        final Button rewardAd = (Button) findViewById(R.id.rewardAd);
        rewardAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ATRewardVideoAd rewardVideoAd = new ATRewardVideoAd(ThirdActivity.this, "b5b728e7a08cd4");
                rewardVideoAd.load();
                rewardVideoAd.setAdListener(new ATRewardVideoListener() {
                    @Override
                    public void onRewardedVideoAdLoaded() {
                        Log.v("rewardAd", "load success");
                        rewardVideoAd.show();
                    }

                    @Override
                    public void onRewardedVideoAdFailed(AdError adError) {
                        Log.v("rewardAd", "load failed");
                    }

                    @Override
                    public void onRewardedVideoAdPlayStart(ATAdInfo atAdInfo) {
                        Log.v("rewardAd", "ad play start");
                    }

                    @Override
                    public void onRewardedVideoAdPlayEnd(ATAdInfo atAdInfo) {
                        Log.v("rewardAd", "ad play end");
                    }

                    @Override
                    public void onRewardedVideoAdPlayFailed(AdError adError, ATAdInfo atAdInfo) {
                        Log.v("rewardAd", "ad play failed");
                    }

                    @Override
                    public void onRewardedVideoAdClosed(ATAdInfo atAdInfo) {
                        Log.v("rewardAd", "ad closed");
                    }

                    @Override
                    public void onRewardedVideoAdPlayClicked(ATAdInfo atAdInfo) {
                        Log.v("rewardAd", "ad clicked");
                    }

                    @Override
                    public void onReward(ATAdInfo atAdInfo) {
                        Log.v("rewardAd", "get reward");
                    }
                });
//                if (rewardVideoAd.isAdReady()){
//                    rewardVideoAd.show();
//                }else {
//
//                }

            }
        });

        float ratio = 320 / 50f;//此比例需与TopOn后台Banner的比例相同
        final int width = getResources().getDisplayMetrics().widthPixels;//定一个宽度值，比如屏幕宽度
        final int height = (int) (width / ratio);
        bannerAd = (Button)findViewById(R.id.bannerAd);
        frameLayout = (FrameLayout)findViewById(R.id._frameLayout);
        bannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ATBannerView bannerAd = new ATBannerView(ThirdActivity.this);
                bannerAd.setUnitId("b5baca45138428");
                frameLayout.addView(bannerAd, new FrameLayout.LayoutParams(width, height));
                bannerAd.setBannerAdListener(new ATBannerListener() {
                    @Override
                    public void onBannerLoaded() {
                        Log.d("bannerAd", "onLoad");
                    }

                    @Override
                    public void onBannerFailed(AdError adError) {
                        adError.printStackTrace();
                        Log.d("bannerAd", "onFailed");
                    }

                    @Override
                    public void onBannerClicked(ATAdInfo atAdInfo) {
                        Log.d("bannerAd", "onClicked");
                    }

                    @Override
                    public void onBannerShow(ATAdInfo atAdInfo) {
                        Log.d("bannerAd", "onShow");
                    }

                    @Override
                    public void onBannerClose(ATAdInfo atAdInfo) {
                        Log.d("bannerAd", "onClose");
                    }

                    @Override
                    public void onBannerAutoRefreshed(ATAdInfo atAdInfo) {
                        Log.d("bannerAd", "onRefreshed");
                    }

                    @Override
                    public void onBannerAutoRefreshFail(AdError adError) {
                        Log.d("bannerAd", "onRefreshedFailed");
                    }
                });
                bannerAd.loadAd();
            }
        });

        findViewById(R.id.interstitialAd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ATInterstitial interstitial = new ATInterstitial(ThirdActivity.this, "b5baca585a8fef");
                interstitial.setAdListener(new ATInterstitialListener() {
                    @Override
                    public void onInterstitialAdLoaded() {
                        Log.v("interstitialAd", "load success");
//                        interstitial.show();
                    }

                    @Override
                    public void onInterstitialAdLoadFail(AdError adError) {
                        Log.v("interstitialAd", "load failed");
                    }

                    @Override
                    public void onInterstitialAdClicked(ATAdInfo atAdInfo) {
                        Log.v("interstitialAd", "ad clicked");
                    }

                    @Override
                    public void onInterstitialAdShow(ATAdInfo atAdInfo) {
                        Log.v("interstitialAd", "ad showed");
                    }

                    @Override
                    public void onInterstitialAdClose(ATAdInfo atAdInfo) {
                        Log.v("interstitialAd", "ad closed");
                    }

                    @Override
                    public void onInterstitialAdVideoStart(ATAdInfo atAdInfo) {
                        Log.v("interstitial", "ad start");
                    }

                    @Override
                    public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo) {
                        Log.v("interstitialAd", "ad end");
                    }

                    @Override
                    public void onInterstitialAdVideoError(AdError adError) {
                        Log.v("interstitialAd", "ad error");
                    }
                });
                if (interstitial.isAdReady()){
                    interstitial.show();
                }else{
                    interstitial.load();
                }

            }
        });

    }

    private void loadNativeAd(){
        if (atNative != null){
            int padding = dp2px(5);
            final int adViewWidth = getResources().getDisplayMetrics().widthPixels - 2 * padding;
            int adViewHeight = dp2px(100 - (2 * padding));

            Map<String, Object> localMap = new HashMap<>();

//            //Since v5.6.4
//            localMap.put(ATAdConst.KEY.AD_WIDTH, adViewWidth);
//            localMap.put(ATAdConst.KEY.AD_HEIGHT, adViewHeight);

            //Mintegral (Support in v5.5.9)
            localMap.put("adViewWidth", adViewWidth);
            localMap.put("adViewHeight", adViewHeight);
            atNative.setLocalExtra(localMap);
            atNative.makeAdRequest();
        }
    }

    private void showNativeAd(){
        if (atNative == null){
            return;
        }

        NativeAd nativeAd = atNative.getNativeAd();
        if (nativeAd != null){
            ATNativeAdView nativeAdView = new ATNativeAdView(this);
            nativeAd.setNativeEventListener(new ATNativeEventListener() {
                @Override
                public void onAdImpressed(ATNativeAdView atNativeAdView, ATAdInfo atAdInfo) {

                }

                @Override
                public void onAdClicked(ATNativeAdView atNativeAdView, ATAdInfo atAdInfo) {

                }

                @Override
                public void onAdVideoStart(ATNativeAdView atNativeAdView) {

                }

                @Override
                public void onAdVideoEnd(ATNativeAdView atNativeAdView) {

                }

                @Override
                public void onAdVideoProgress(ATNativeAdView atNativeAdView, int i) {

                }
            });
            NativeDemoRender render = new NativeDemoRender(this);
            nativeAd.renderAdView(nativeAdView, render);
            nativeAd.prepare(nativeAdView, render.getClickView(), null);
            frameLayout.addView(nativeAdView);
        }

    }

    private int dp2px(int i) {
        return Math.round(getResources().getDisplayMetrics().density * i);
    }

}
