//package com.test.createwrapper.target;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//
////import com.ads.AdSdk;
////import com.ads.common.AdLog;
////import com.ads.natives.NativeAdData;
////import com.ads.natives.NativeLoadAdListener;
//import com.anythink.banner.api.ATBannerListener;
//import com.anythink.banner.api.ATBannerView;
//import com.anythink.core.api.ATAdInfo;
//import com.anythink.core.api.AdError;
//import com.test.createwrapper.wapper.ATBannerViewWrapper;
////import com.fishgames.dfish.ads.AdNetworkUtils;
////import com.fishgames.dfish.ads.BaseAdViewData;
////import com.fishgames.dfish.ads.config.NativeAdConfig;
////import com.fishgames.dfish.BuildConfig;
////import com.fishgames.dfish.sdk.GameLib;
//
//import java.util.Map;
//
///**
// * Created by JINSHUI on 2019/8/26
// */
//public class TopOnBannerData extends NativeAdData<BaseAdViewData> {
//    private final String TAG = "TopOnBannerData";
//
//    private ATBannerView mAdmobBannerAd;
//
//    private int bannerWidth = 640;
//    private int bannerHeight = 100;
//
//    public TopOnBannerData(Context context, String placementKey) {
//        super(context, placementKey);
//        if (isTestAd()) {
//            // TopOn测试id需要可以分平台测试
//            mUnitId = "b5baca41a2536f";     // admob
////            mUnitId = "b5bbdc51a35e29";     // facebook
////            mUnitId = "b5dd388839bf5e";     // mtg
////            mUnitId = "b5baca45138428";     // pangle(穿山甲)
////            mUnitId = "b5bbdc59f88520";     // applovin 测试id, 一般使用这个，成功率高
//        }
//
//        float ratio = 320 / 50f;    //此比例需与TopOn后台Banner的比例相同
//        bannerWidth = context.getResources().getDisplayMetrics().widthPixels;//定一个宽度值，比如屏幕宽度
//        bannerHeight = (int) (bannerWidth / ratio);
//    }
//
//    public boolean isTestAd(){
//        return BuildConfig.DEBUG;
//    }
//
//    @Override
//    protected Map<String, String> getIDs() {
//        return NativeAdConfig.TOPON_UNIT_ID;
//    }
//
//    @Override
//    public String getName() {
//        return AdSdk.AD_TOPON_NAME;
//    }
//
//    @Override
//    public void loadNative(NativeLoadAdListener loadNativeAdListener) {
//        super.loadNative(loadNativeAdListener);
//
//        mAdmobBannerAd = new ATBannerView(GameLib.MAIN_ACTIVITY);
//        // 目前只支持50dp的高度的banner
////        mAdmobBannerAd.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, dip2px(mContext, 50)));
//        mAdmobBannerAd.setUnitId(mUnitId);
//
//        mAdmobBannerAd.setBannerAdListener(new ATBannerListener() {
//            @Override
//            public void onBannerLoaded() {
//                AdLog.v(TAG, "" + getName() + " 横幅 onBannerLoaded");
//                onAdLoadFinish();
//            }
//
//            @Override
//            public void onBannerFailed(AdError adError) {
//                AdLog.v(TAG, "" + getName() + " 横幅 onBannerFailed" + adError.printStackTrace());
//                onAdError(0, adError.printStackTrace());
//            }
//
//            @Override
//            public void onBannerClicked(ATAdInfo atAdInfo) {
//                AdLog.v(TAG, "" + getName() + " 横幅 onBannerClicked" + " netWork: " + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                setPublisherRevenue(atAdInfo.getPublisherRevenue());
//                onNativeAdClick();
//            }
//
//            @Override
//            public void onBannerShow(ATAdInfo atAdInfo) {
//                AdLog.v(TAG, "" + getName() + " 横幅 onBannerShow NetworkPlacementId: " + atAdInfo.getNetworkPlacementId() + " netWork: " + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                setPublisherRevenue(atAdInfo.getPublisherRevenue());
//            }
//
//            @Override
//            public void onBannerClose(ATAdInfo atAdInfo) {
//
//            }
//
//            @Override
//            public void onBannerAutoRefreshed(ATAdInfo atAdInfo) {
//                setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                setPublisherRevenue(atAdInfo.getPublisherRevenue());
//            }
//
//            @Override
//            public void onBannerAutoRefreshFail(AdError adError) {
//
//            }
//        });
//        mAdmobBannerAd.loadAd();
//    }
//
//    @Override
//    public void bindView(BaseAdViewData viewData) {
//        if (mAdmobBannerAd != null) {
//            viewData.mIsLoadIConComplete = true;
//            viewData.mIsLoadCoverComplete = true;
//            if (viewData.mLoadingView != null) {
//                viewData.mLoadingView.setVisibility(View.GONE);
//            }
//            if (viewData.mBannerAdView != null) {
//                viewData.mBannerAdView.setVisibility(View.VISIBLE);
//            }
//            if (viewData.mBannerAdView != null) {
//                if (viewData.mBannerAdView.getChildCount() > 0) {
//                    viewData.mBannerAdView.removeAllViews();
//                }
//                if (mAdmobBannerAd.getParent() != null) {
//                    ((ViewGroup) mAdmobBannerAd.getParent()).removeView(mAdmobBannerAd);
//                }
//                // Add the Native Express ad to the native express ad viewData.
//                viewData.mBannerAdView.addView(mAdmobBannerAd, bannerWidth, bannerHeight);
//            }
//            viewData.updateLoadingView();  //加载去广告动画
//        }
//    }
//
//    @Override
//    public void unbind(BaseAdViewData adViewData) {
//
//    }
//
//    @Override
//    public void recycle() {
//        super.recycle();
//        if (mAdmobBannerAd != null) {
//            mAdmobBannerAd.clean();
//        }
//    }
//
//    @Override
//    public int getAdPlatform() {
//        return AdSdk.AD_ADMOB;
//    }
//
////    /**
////     * 自己根据广告位实现
////     *
////     * @param placementKey
////     * @return
////     */
////    private AdSize createSize(String placementKey) {
////        switch (placementKey) {
////            case NativeAdConfig.banner_bottom:
////                return new AdSize(320, 50);
////            case NativeAdConfig.banner_center:
////                return new AdSize(300, 250);
////            default:
////                return new AdSize(320, 50);
////        }
////    }
//
//    private int dip2px(Context context, float dipValue) {
//        float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dipValue * scale + 0.5f);
//    }
//}