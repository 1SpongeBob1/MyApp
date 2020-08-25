package com.test.addemo;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.anythink.nativead.api.ATNativeAdRenderer;
import com.anythink.nativead.unitgroup.api.CustomNativeAd;

import java.util.ArrayList;
import java.util.List;

public class NativeDemoRender implements ATNativeAdRenderer<CustomNativeAd> {

    Context mContext;
    List<View> mClickView = new ArrayList<>();

    public NativeDemoRender(Context context) {
        mContext = context;
    }

    View mDevelopView;
    int mNetworkType;

    @Override
    public View createView(Context context, int networkType) {
        if (mDevelopView == null) {
            mDevelopView = LayoutInflater.from(context).inflate(R.layout.native_ad_item, null);
        }
        mNetworkType = networkType;
        if (mDevelopView.getParent() != null) {
            ((ViewGroup) mDevelopView.getParent()).removeView(mDevelopView);
        }
        return mDevelopView;
    }

    @Override
    public void renderAdView(View view, CustomNativeAd ad) {
        mClickView.clear();
        TextView titleView = (TextView) view.findViewById(R.id.native_ad_title);
        TextView descView = (TextView) view.findViewById(R.id.native_ad_desc);
        TextView ctaView = (TextView) view.findViewById(R.id.native_ad_install_btn);
        TextView adFromView = (TextView) view.findViewById(R.id.native_ad_from);
        FrameLayout contentArea = (FrameLayout) view.findViewById(R.id.native_ad_content_image_area);
        FrameLayout iconArea = (FrameLayout) view.findViewById(R.id.native_ad_image);

        titleView.setText("this is title");
        descView.setText("this is desc");
        ctaView.setText("this is cta");
        adFromView.setText("this is ad from");
        contentArea.removeAllViews();
        iconArea.removeAllViews();

        View mediaView = ad.getAdMediaView(contentArea, contentArea.getWidth());

//        if (ad.isNativeExpress()) {
//            titleView.setVisibility(View.GONE);
//            descView.setVisibility(View.GONE);
//            ctaView.setVisibility(View.GONE);
//            iconArea.setVisibility(View.GONE);
//            if(mediaView.getParent() != null) {
//                ((ViewGroup) mediaView.getParent()).removeView(mediaView);
//            }
//
//            contentArea.addView(mediaView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//            return;
//        }


        titleView.setVisibility(View.VISIBLE);
        descView.setVisibility(View.VISIBLE);
        ctaView.setVisibility(View.VISIBLE);
        iconArea.setVisibility(View.VISIBLE);

        if (mediaView != null) {
            if(mediaView.getParent() != null) {
                ((ViewGroup) mediaView.getParent()).removeView(mediaView);
            }

            contentArea.addView(mediaView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

        }

        if (!TextUtils.isEmpty(ad.getAdFrom())) {
            adFromView.setText(ad.getAdFrom() != null ? ad.getAdFrom() : "");
            adFromView.setVisibility(View.VISIBLE);
        } else {
            adFromView.setVisibility(View.GONE);
        }
        titleView.setText("this is title");
        descView.setText("this is desc");
        ctaView.setText("this is cta view");
        mClickView.add(titleView);
        mClickView.add(descView);
        mClickView.add(ctaView);

    }

    public List<View> getClickView() {
        return mClickView;
    }
}
