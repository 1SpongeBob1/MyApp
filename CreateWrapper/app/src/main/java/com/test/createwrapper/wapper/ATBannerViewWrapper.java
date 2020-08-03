package com.test.createwrapper.wapper;

import android.app.Activity;
import android.content.Context;
import android.widget.FrameLayout;

import com.anythink.banner.api.ATBannerListener;
import com.anythink.banner.api.ATBannerView;
import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.AdError;
import com.test.createwrapper.MainActivity;

public class ATBannerViewWrapper {
    private ATBannerView mBannerView;
    private ATBannerListener mListener;
    private int width;
    private int height;

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

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setView(Context context){
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.addView(mBannerView, new FrameLayout.LayoutParams(200, 50));
    }
}