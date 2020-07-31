//package com.test.createwrapper.target;
//
//import android.content.Context;
//import android.text.TextUtils;
//
//import com.ads.AdSdk;
//import com.ads.common.AdLog;
//import com.ads.intstitls.IntstitlAdData;
//import com.anythink.core.api.ATAdInfo;
//import com.anythink.core.api.AdError;
//import com.anythink.interstitial.api.ATInterstitial;
//import com.anythink.interstitial.api.ATInterstitialListener;
//import com.fishgames.dfish.ads.AdNetworkUtils;
//import com.fishgames.dfish.ads.config.IntstitlAdconfig;
//import com.fishgames.dfish.BuildConfig;
//import com.fishgames.dfish.sdk.GameLib;
//
//import java.util.Map;
//
///**
// * Created by JINSHUI on 2019/8/26
// */
//public class TopOnInterstitialAdData extends IntstitlAdData {
//    private static String TAG = "TopOnInterstitialAdData";
//
//    private final static boolean IS_TEST = BuildConfig.DEBUG;
//    private ATInterstitial interstitialAd = null;
//
//    @Override
//    public String getName() {
//        return AdSdk.AD_TOPON_NAME;
//    }
//
//    public TopOnInterstitialAdData(Context context, String placementKey) {
//        super(context, placementKey);
//        if (IS_TEST){
//            // TopOn测试id需要可以分平台测试
//            mUnitId = "b5baca54674522";     // admob
////            mUnitId = "b5bbdc6fc65dd1";     // applovin
////            mUnitId = "b5bbdc69a21187";     // facebook
////            mUnitId = "b5bbdc8e9ef916";     // ironsource
////            mUnitId = "b5bbdc725768fa";     // mtg
////            mUnitId = "b5baca585a8fef";     // pangle (穿山甲)
////            mUnitId = "b5c21a303c25e0";     // unity
////            mUnitId = "b5bbdc9182f9f2";     // vungle
////            mUnitId = "b5e620f6c41f71";     // pangle 海外测试，需要对应 appid："a5e620f43d975c"  appKey："4f7b9ac17decb9babec83aac078742c7"
//        }
//    }
//
//    @Override
//    protected void clearData() {
//        /*if (mAdmobInterstitialAd != null) {
//            mAdmobInterstitialAd = null;
//        }*/
//    }
//
//    @Override
//    public void recycle() {
//        super.recycle();
//    }
//
//    @Override
//    protected boolean show() {
//        if (interstitialAd != null && interstitialAd.isAdReady()) {
//            interstitialAd.show();
//            return true;
//        } else {
//            /**
//             * 重新读取
//             */
//            if (interstitialAd != null) {
//                interstitialAd.load();
//                adLoadStart();
//            }
//            return false;
//        }
//    }
//
//    @Override
//    protected Map<String, String> getUnitIds() {
//        return IntstitlAdconfig.TOPON_UNITY_IDS;
//    }
//
//    @Override
//    protected boolean canShow() {
//        if (isMainThead()){
//            return interstitialAd != null && interstitialAd.isAdReady();
//        }else {
//            return mHasAd;
//        }
//    }
//
//    @Override
//    public void loadAd( ) {
//        if (mStartLoading) return;
//        if (canShow()){
//            adLoaded();
//            return;
//        }
//        super.loadAd();
//        if (TextUtils.isEmpty(mUnitId)){
//            return;
//        }
//        if (interstitialAd == null){
//            interstitialAd = new ATInterstitial(GameLib.MAIN_ACTIVITY, mUnitId);
//        }
//        interstitialAd.setAdListener(new ATInterstitialListener() {
//            @Override
//            public void onInterstitialAdLoaded() {
//                AdLog.v(TAG, "" + getName() + " 插屏 onAdLoaded");
//                adLoaded();
//            }
//
//            @Override
//            public void onInterstitialAdLoadFail(AdError adError) {
//                AdLog.v(TAG, "" + getName() + " 插屏 onAdFailedToLoad " + adError.printStackTrace());
//                // 2005 正在读取中
//                if (!TextUtils.equals(adError.getCode(), "2005")){
//                    adFailToLoad();
//                }
//            }
//
//            @Override
//            public void onInterstitialAdClicked(ATAdInfo atAdInfo) {
//                AdLog.v(TAG, "" + getName() + " 插屏 onAdClicked" + " netWork: " + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                setPublisherRevenue(atAdInfo.getPublisherRevenue());
//                adClicked();
//            }
//
//            @Override
//            public void onInterstitialAdShow(ATAdInfo atAdInfo) {
//                AdLog.v(TAG, "" + getName() + " 插屏 onAdOpened" + " netWork: " + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()) + " networkUnitId: " + atAdInfo.getNetworkPlacementId());
//                setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                setPublisherRevenue(atAdInfo.getPublisherRevenue());
//                adOpened();
//            }
//
//            @Override
//            public void onInterstitialAdClose(ATAdInfo atAdInfo) {
//                AdLog.v(TAG, "" + getName() + " 插屏 onAdClosed" + " netWork: " + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                setPublisherRevenue(atAdInfo.getPublisherRevenue());
//                adClosed();
//                interstitialAd.load();
//                adLoadStart();
//            }
//
//            @Override
//            public void onInterstitialAdVideoStart(ATAdInfo atAdInfo) {
//                setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                setPublisherRevenue(atAdInfo.getPublisherRevenue());
//            }
//
//            @Override
//            public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo) {
//                setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                setPublisherRevenue(atAdInfo.getPublisherRevenue());
//            }
//
//            @Override
//            public void onInterstitialAdVideoError(AdError adError) {
//
//            }
//        });
//        if (!interstitialAd.isAdReady()) {
//            interstitialAd.load();
//            adLoadStart();
//        }
//    }
//}
