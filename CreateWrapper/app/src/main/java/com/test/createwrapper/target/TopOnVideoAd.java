//package com.test.createwrapper.target;
//
//import android.app.Activity;
//import android.os.Looper;
//import android.text.TextUtils;
//
//import com.ads.AdSdk;
//import com.ads.common.AdLog;
//import com.ads.videos.VdAdData;
//import com.anythink.core.api.ATAdInfo;
//import com.anythink.core.api.AdError;
//import com.anythink.rewardvideo.api.ATRewardVideoAd;
//import com.anythink.rewardvideo.api.ATRewardVideoListener;
//import com.fishgames.dfish.ads.AdNetworkUtils;
//import com.fishgames.dfish.ads.config.VideoAdConfig;
//import com.fishgames.dfish.BuildConfig;
//
///**
// * Created by JINSHUI on 2019/8/26
// */
//public class TopOnVideoAd extends VdAdData {
//    private final String TAG = "TopOnVideoAd";
//
//    private ATRewardVideoAd mRewardedVideoAd;
//    /**
//     * 视频奖励回调
//     */
//    private ATRewardVideoListener rewardedVideoAdListener = new ATRewardVideoListener() {
//        @Override
//        public void onRewardedVideoAdLoaded() {
//            AdLog.v(TAG, "" + getName() + " onRewardedVideoAdLoaded");
//            HANDLER_POST.post(new Runnable() {
//                @Override
//                public void run() {
//                    videoAdLoaded();
//                }
//            });
//        }
//
//        @Override
//        public void onRewardedVideoAdFailed(final AdError adError) {
//            AdLog.e(TAG, "" + getName() + " onRewardedVideoFail" + adError.printStackTrace());
//            HANDLER_POST.post(new Runnable() {
//                @Override
//                public void run() {
//                    if (!mAdOpended && !TextUtils.equals(adError.getCode(), "2005")){
//                        videoAdFaiToLoaded(0, adError.printStackTrace());
//                    }
//                }
//            });
//        }
//
//        @Override
//        public void onRewardedVideoAdPlayStart(final ATAdInfo atAdInfo) {
//            AdLog.v(TAG, "" + getName() + " onRewardedVideoAdOpened"+ " netWork: " + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//            HANDLER_POST.post(new Runnable() {
//                @Override
//                public void run() {
//                    setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                    setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                    setPublisherRevenue(atAdInfo.getPublisherRevenue());
//                    videoAdOpen();
//                }
//            });
//        }
//
//        @Override
//        public void onRewardedVideoAdPlayEnd(ATAdInfo atAdInfo) {
//            AdLog.v(TAG, "" + getName() + " onRewardedVideoAdPlayEnd"+ " netWork: " + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//        }
//
//        @Override
//        public void onRewardedVideoAdPlayFailed(AdError adError, final ATAdInfo atAdInfo) {
//            HANDLER_POST.post(new Runnable() {
//                @Override
//                public void run() {
//                    // 播放失败
//                    videoAdFaiToLoaded(0, "");
//                    setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                    setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                    setPublisherRevenue(atAdInfo.getPublisherRevenue());
//                    videoAdClosed();
//                }
//            });
//        }
//
//        @Override
//        public void onRewardedVideoAdClosed(final ATAdInfo atAdInfo) {
//            HANDLER_POST.post(new Runnable() {
//                @Override
//                public void run() {
//                    AdLog.v(TAG, "" + getName() + " onRewardedVideoAdClosed"+ " netWork: " + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
////                    if (b){
////                        AdLog.e(TAG, "" + getName() + " onRewardedVideoAdRewarded");
////                        videoAdRewarded();
////                    }
//                    setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                    setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                    setPublisherRevenue(atAdInfo.getPublisherRevenue());
//                    videoAdClosed();
//                }
//            });
//        }
//
//        @Override
//        public void onRewardedVideoAdPlayClicked(final ATAdInfo atAdInfo) {
//            HANDLER_POST.post(new Runnable() {
//                @Override
//                public void run() {
//                    AdLog.v(TAG, "" + getName() + " onRewardedVideoAdPlayClicked" + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                    // 播放失败
//                    setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                    setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                    setPublisherRevenue(atAdInfo.getPublisherRevenue());
//                    videoAdClick();
//                }
//            });
//        }
//
//        @Override
//        public void onReward(final ATAdInfo atAdInfo) {
//            HANDLER_POST.post(new Runnable() {
//                @Override
//                public void run() {
//                    AdLog.v(TAG, "" + getName() + " onReward"+ " netWork: " + AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                    setNetworkSdk(AdNetworkUtils.getNetworkSdkTopon(atAdInfo.getNetworkFirmId()));
//                    setNetworkUnitId(atAdInfo.getNetworkPlacementId());
//                    setPublisherRevenue(atAdInfo.getPublisherRevenue());
//                    videoAdRewarded();
//                }
//            });
//        }
//    };
//
//    public TopOnVideoAd(Activity context, String placementKey) {
//        super(context, placementKey);
//        setNetworkSdk(AdSdk.AD_TOPON_NAME);
//    }
//
//    @Override
//    public String getUnitId() {
//        if (BuildConfig.DEBUG){
//            // TopOn测试id需要可以分平台测试
//            return "b5b449f025ec7c";    // admob
////            return "b5b449f20155a7";    // applovin
////            return "b5b449eefcab50";    // facebook
////            return "b5b449f75948c5";    // ironsource
////            return "b5b449f2f58cd7";    // mtg (mobvista)
////            return "b5b728e7a08cd4";    // pangle(穿山甲)
////            return "b5b449f809139c";    // unityads
////            return "b5b449f97e0b5f";    // vungle
////            return "b5e620f5c18da2";    // pangle 海外测试，需要对应 appid："a5e620f43d975c"  appKey："4f7b9ac17decb9babec83aac078742c7"
//        }
//        return VideoAdConfig.TOPON_UNIT_ID.get(mPlacementKey);
//    }
//
//    @Override
//    public String getName() {
//        return AdSdk.AD_TOPON_NAME;
//    }
//
//    @Override
//    public void playVideoAd() {
//        if (mRewardedVideoAd != null && mRewardedVideoAd.isAdReady()){
//            mRewardedVideoAd.setAdListener(rewardedVideoAdListener);
//            mRewardedVideoAd.show();
//        }else {
//            AdLog.e(TAG, getName() + " 播放失败");
//        }
//    }
//
//    @Override
//    public boolean canPlay() {
//        if (mRewardedVideoAd == null) return false;
//        if (isMainThead()){
//            if (mRewardedVideoAd.isAdReady()){
//                mHasAd = true;
//                return true;
//            }
//            return mRewardedVideoAd.isAdReady();
//        }
//        return mHasAd;
//    }
//
//    @Override
//    public boolean isMainThead() {
//        try{
//            if (Looper.getMainLooper() == Looper.myLooper()){
//                return true;
//            }
//        }catch (Exception e){}
//
//        boolean isMainThead = Looper.getMainLooper().getThread() == Thread.currentThread();
//        AdLog.v("AdData", " 是否在主线程： " + isMainThead);
//        return isMainThead;
//    }
//
//    @Override
//    public void loadVideoAd() {
//        if (!isValidate() || mIsStartLoadAd){
//            return;
//        }
//        if (mRewardedVideoAd != null && mRewardedVideoAd.isAdReady()){
//            AdLog.v(getName(), getName() + ": 缓存读取成功");
//            videoAdLoaded();
//            return;
//        }
//        super.loadVideoAd();
//        if (null == mRewardedVideoAd){
//            mRewardedVideoAd = new ATRewardVideoAd(mContext, mUnitId);
////            // facebook network
////            FacebookUpArpuRewardedVideoSetting facebookUpArpuRewardedVideoSetting = new FacebookUpArpuRewardedVideoSetting();
////            mRewardedVideoAd.addSetting(FacebookUpArpuConst.NETWORK_FIRM_ID, facebookUpArpuRewardedVideoSetting);
////            // admob network
////            AdmobUpArpuRewardedVideoSetting _admobUpArpuMediationSetting = new AdmobUpArpuRewardedVideoSetting();
////            mRewardedVideoAd.addSetting(AdmobUpArpuConst.NETWORK_FIRM_ID, _admobUpArpuMediationSetting);
////            // ironsource network
////            IronsourceUpArpuRewardedVideoSettin _ironsourceUpArpuMediationSetting = new IronsourceUpArpuRewardedVideoSetting();
////            mRewardedVideoAd.addSetting(IronsourceUparpuConst.NETWORK_FIRM_ID, _ironsourceUpArpuMediationSetting);
////            // applovin network
////            ApplovinUpArpuRewardedVideoSetting _applovinUpArpuMediationSetting = new ApplovinUpArpuRewardedVideoSetting();
////            mRewardedVideoAd.addSetting(ApplovinUpArpuConst.NETWORK_FIRM_ID, _applovinUpArpuMediationSetting);
////            // unitys network
////            UnityAdsUpArpuRewardedVideoSetting _unityAdUpArpuMediationSetting = new UnityAdsUpArpuRewardedVideoSetting();
////            mRewardedVideoAd.addSetting(UnityAdsUpArpuConst.NETWORK_FIRM_ID, _unityAdUpArpuMediationSetting);
////            mRewardedVideoAd.addSetting(AdmobATConst.NETWORK_FIRM_ID, new AdmobRewardedVideoSetting());
//            mRewardedVideoAd.setAdListener(rewardedVideoAdListener);
//        }
//        mRewardedVideoAd.load();
//        videoAdLoadStart();
//    }
//
//    @Override
//    public void recycle() {
//        super.recycle();
////        if (mRewardedVideoAd != null){
////            mRewardedVideoAd.onDestory();
////        }
//    }
//}
