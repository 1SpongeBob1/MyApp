package com.test.createwrapper.wapper;

import android.app.Activity;

import com.anythink.banner.api.ATBannerListener;
import com.anythink.banner.api.ATBannerView;
import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.AdError;

public class ATBannerViewWrapper {
    private ATBannerView mBannerView;
    private ATBannerListener mListener;

    public ATBannerViewWrapper(Activity activity){
        mBannerView = new ATBannerView(activity);
        mBannerView.setBannerAdListener(new ATBannerListener() {
            @Override
            public void onBannerLoaded() {
                if (mListener != null){
                    mListener.onBannerLoaded();
                }
            }

            @Override
            public void onBannerFailed(AdError adError) {
                mListener.onBannerFailed(adError);
            }

            @Override
            public void onBannerClicked(ATAdInfo atAdInfo) {
                mListener.onBannerClicked(atAdInfo);
            }

            @Override
            public void onBannerShow(ATAdInfo atAdInfo) {
                mListener.onBannerShow(atAdInfo);
            }

            @Override
            public void onBannerClose(ATAdInfo atAdInfo) {
                mListener.onBannerClose(atAdInfo);
            }

            @Override
            public void onBannerAutoRefreshed(ATAdInfo atAdInfo) {
                mListener.onBannerAutoRefreshed(atAdInfo);
            }

            @Override
            public void onBannerAutoRefreshFail(AdError adError) {
                mListener.onBannerAutoRefreshFail(adError);
            }
        });
    }

    public void setUnitID(String unitId){
        mBannerView.setUnitId(unitId);
    }

    public void setListener(ATBannerListener listener){
        mListener = listener;
    }

    public void loadAd(){
        mBannerView.loadAd();
    }

    public void clean(){
        mBannerView.clean();
    }
}