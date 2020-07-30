package com.test.createwrapper.wapper;

import android.app.Activity;

import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.AdError;
import com.anythink.rewardvideo.api.ATRewardVideoAd;
import com.anythink.rewardvideo.api.ATRewardVideoListener;

public class ATRewardVideoAdWrapper {
    private ATRewardVideoAd videoAd;
    private ATRewardVideoListener mListener;

    public ATRewardVideoAdWrapper(Activity activity, String unitId){
        videoAd = new ATRewardVideoAd(activity, unitId);
        videoAd.setAdListener(new ATRewardVideoListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                mListener.onRewardedVideoAdLoaded();
            }

            @Override
            public void onRewardedVideoAdFailed(AdError adError) {
                mListener.onRewardedVideoAdFailed(adError);
            }

            @Override
            public void onRewardedVideoAdPlayStart(ATAdInfo atAdInfo) {
                mListener.onRewardedVideoAdPlayStart(atAdInfo);
            }

            @Override
            public void onRewardedVideoAdPlayEnd(ATAdInfo atAdInfo) {
                mListener.onRewardedVideoAdPlayEnd(atAdInfo);
            }

            @Override
            public void onRewardedVideoAdPlayFailed(AdError adError, ATAdInfo atAdInfo) {
                mListener.onRewardedVideoAdPlayFailed(adError, atAdInfo);
            }

            @Override
            public void onRewardedVideoAdClosed(ATAdInfo atAdInfo) {
                mListener.onRewardedVideoAdClosed(atAdInfo);
            }

            @Override
            public void onRewardedVideoAdPlayClicked(ATAdInfo atAdInfo) {
                mListener.onRewardedVideoAdPlayClicked(atAdInfo);
            }

            @Override
            public void onReward(ATAdInfo atAdInfo) {
                mListener.onReward(atAdInfo);
            }
        });
    }

    public void setListener(ATRewardVideoListener listener){
        mListener = listener;
    }

    public boolean isReady(){
        if (videoAd.isAdReady()){
            return true;
        }else {
            return false;
        }
    }

    public void show(){
        videoAd.show();
    }
}
