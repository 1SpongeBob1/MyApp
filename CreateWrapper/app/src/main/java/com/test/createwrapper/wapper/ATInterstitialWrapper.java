package com.test.createwrapper.wapper;

import android.app.Activity;
import android.content.Context;

import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.AdError;
import com.anythink.interstitial.api.ATInterstitial;
import com.anythink.interstitial.api.ATInterstitialListener;

public class ATInterstitialWrapper {
    private ATInterstitial interstitialAd;
    private ATInterstitialListener mListener;

    public ATInterstitialWrapper(Activity activity, String unitId){
        interstitialAd = new ATInterstitial(activity, unitId);
        interstitialAd.setAdListener(new ATInterstitialListener() {
            @Override
            public void onInterstitialAdLoaded() {
                if (mListener != null){
                    mListener.onInterstitialAdLoaded();
                }
            }

            @Override
            public void onInterstitialAdLoadFail(AdError adError) {
                mListener.onInterstitialAdLoadFail(adError);
            }

            @Override
            public void onInterstitialAdClicked(ATAdInfo atAdInfo) {
                mListener.onInterstitialAdClicked(atAdInfo);
            }

            @Override
            public void onInterstitialAdShow(ATAdInfo atAdInfo) {
                mListener.onInterstitialAdShow(atAdInfo);
            }

            @Override
            public void onInterstitialAdClose(ATAdInfo atAdInfo) {
                mListener.onInterstitialAdClose(atAdInfo);
            }

            @Override
            public void onInterstitialAdVideoStart(ATAdInfo atAdInfo) {
                mListener.onInterstitialAdVideoStart(atAdInfo);
            }

            @Override
            public void onInterstitialAdVideoEnd(ATAdInfo atAdInfo) {
                mListener.onInterstitialAdVideoEnd(atAdInfo);
            }

            @Override
            public void onInterstitialAdVideoError(AdError adError) {
                mListener.onInterstitialAdVideoError(adError);
            }
        });
    }

    public boolean isReady(){
        return interstitialAd.isAdReady();
    }

    public void load(){
        interstitialAd.load();
    }

    public void show(){
        interstitialAd.show();
    }

    public void setListener(ATInterstitialListener listener){
        mListener = listener;
    }

}
