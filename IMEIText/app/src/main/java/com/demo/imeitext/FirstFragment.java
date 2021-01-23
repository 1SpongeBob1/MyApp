package com.demo.imeitext;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.AdError;
import com.anythink.rewardvideo.api.ATRewardVideoAd;
import com.anythink.rewardvideo.api.ATRewardVideoListener;
import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighlightOptions;
import com.app.hubert.guide.model.RelativeGuide;
import com.arthenica.mobileffmpeg.Config;
import com.arthenica.mobileffmpeg.ExecuteCallback;
import com.arthenica.mobileffmpeg.FFmpeg;
import com.arthenica.mobileffmpeg.FFprobe;
import com.demo.imeitext.databinding.FragmentFirstBinding;
import com.takusemba.spotlight.OnSpotlightListener;
import com.takusemba.spotlight.OnTargetListener;
import com.takusemba.spotlight.Spotlight;
import com.takusemba.spotlight.Target;
import com.takusemba.spotlight.shape.Circle;
import com.takusemba.spotlight.shape.RoundedRectangle;

import zhy.com.highlight.HighLight;
import zhy.com.highlight.interfaces.HighLightInterface;
import zhy.com.highlight.position.OnBottomPosCallback;
import zhy.com.highlight.position.OnLeftPosCallback;
import zhy.com.highlight.position.OnRightPosCallback;
import zhy.com.highlight.position.OnTopPosCallback;
import zhy.com.highlight.shape.BaseLightShape;
import zhy.com.highlight.shape.CircleLightShape;
import zhy.com.highlight.shape.OvalLightShape;
import zhy.com.highlight.shape.RectLightShape;
import zhy.com.highlight.view.HightLightView;

import static com.arthenica.mobileffmpeg.Config.RETURN_CODE_CANCEL;
import static com.arthenica.mobileffmpeg.Config.RETURN_CODE_SUCCESS;

public class FirstFragment extends Fragment {
    FragmentFirstBinding binding;
    HighLight mHighLight;
    Controller guide;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.firstJumpButton.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(R.id.secondFragment);
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 判断手机是否熄屏
                if (isScreenOff(getContext())){
                    PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
                    PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "callmaster:TAG");
                    wakeLock.acquire(10*60*1000L /*10 minutes*/);
                    Intent intent = new Intent(getContext(), TestActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(intent);
                }
            }
        };

        new Thread(runnable).start();

        binding.firstAdButton.setOnClickListener(v->{
            //展示广告
            ATRewardVideoAd ad = new ATRewardVideoAd(requireActivity(), MainActivity.REWARD_AD);
            ad.setAdListener(new ATRewardVideoListener() {
                @Override
                public void onRewardedVideoAdLoaded() {
                    ad.show(requireActivity());
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
            ad.load();
            if (ad.isAdReady()){
                ad.show(requireActivity());
            }
        });

//        showSpotlight();
//        showHighLight();
//        showHighLightNormal();

        HighlightOptions options = new  HighlightOptions.Builder().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "on click", Toast.LENGTH_SHORT).show();
                if (guide != null){
                    guide.remove();
                }

            }
        }).build();
        guide = NewbieGuide.with(requireActivity())
                .setLabel("Guide Test")
                .setShowCounts(3)
                .alwaysShow(false)
                .addGuidePage(GuidePage.newInstance()
                        .addHighLightWithOptions(binding.firstJumpButton, options)
                        .addHighLight(binding.firstJumpButton, new RelativeGuide(R.layout.guide_test, Gravity.BOTTOM))
                        .setEverywhereCancelable(false)
                        )
                .build();
        guide.show();

        int rc = FFmpeg.execute("-i file1.mp4 -c:v mpeg4 file2.mp4");
        long executionId = FFmpeg.executeAsync("-i file1.mp4 -c:v mpeg4 file2.mp4", new ExecuteCallback() {

            @Override
            public void apply(final long executionId, final int returnCode) {
                if (rc == RETURN_CODE_SUCCESS) {
                    Log.i(Config.TAG, "Async command execution completed successfully.");
                } else if (rc == RETURN_CODE_CANCEL) {
                    Log.i(Config.TAG, "Async command execution cancelled by user.");
                } else {
                    Log.i(Config.TAG, String.format("Async command execution failed with rc=%d.", rc));
                }
            }
        });



    }

    private void showSpotlight(){
        Target target = new Target.Builder()
                .setAnchor(binding.firstJumpButton)
                .setShape(new RoundedRectangle(100, 100, 10, 100))
                .setOverlay(new FrameLayout(requireContext()))
                .setOnTargetListener(new OnTargetListener() {
                    @Override
                    public void onStarted() {
                        Toast.makeText(requireContext(), "first target is start", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onEnded() {
                        Toast.makeText(requireContext(), "first target is end", Toast.LENGTH_SHORT).show();
                    }
                }).build();

        Spotlight spotlight = new Spotlight.Builder(requireActivity())
                .setTargets(target)
                .setBackgroundColor(Color.parseColor("#88000000"))
                .setDuration(1000L)
                .setAnimation(new DecelerateInterpolator(2f))
                .setOnSpotlightListener(new OnSpotlightListener() {
                    @Override
                    public void onStarted() {

                    }

                    @Override
                    public void onEnded() {

                    }
                }).build();

        spotlight.start();
    }

    private void showHighLight(){
        HighLight highLight = new HighLight(requireContext());
        highLight.autoRemove(false).intercept(true).enableNext().anchor(binding.getRoot()).addHighLight(binding.firstJumpButton,R.mipmap.guide1,
                new OnTopPosCallback(20), new RectLightShape(0,0,15,0,0))
                .addHighLight(binding.firstJumpButton,R.mipmap.guide2,new OnRightPosCallback(5),new BaseLightShape(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5,getResources().getDisplayMetrics()), TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5,getResources().getDisplayMetrics()),0) {
                    @Override
                    protected void resetRectF4Shape(RectF viewPosInfoRectF, float dx, float dy) {
                        //缩小高亮控件范围
                        viewPosInfoRectF.inset(dx,dy);
                    }

                    @Override
                    protected void drawShape(Bitmap bitmap, HighLight.ViewPosInfo viewPosInfo) {
                        //custom your hight light shape 自定义高亮形状
                        Canvas canvas = new Canvas(bitmap);
                        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                        paint.setDither(true);
                        paint.setAntiAlias(true);
                        //blurRadius必须大于0
                        if(blurRadius>0){
                            paint.setMaskFilter(new BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.SOLID));
                        }
                        RectF rectF = viewPosInfo.rectF;
                        canvas.drawOval(rectF, paint);
                    }
                })
                .addHighLight(binding.firstJumpButton,R.mipmap.guide3,new OnTopPosCallback(),new CircleLightShape())
                .addHighLight(getView(), R.mipmap.guide4, new OnBottomPosCallback(10), new OvalLightShape(5,5,20))
                .setOnRemoveCallback(new HighLightInterface.OnRemoveCallback() {//监听移除回调
                    @Override
                    public void onRemove() {
                        Toast.makeText(requireContext(), "The HightLight view has been removed", Toast.LENGTH_SHORT).show();

                    }
                })
                .setOnShowCallback(new HighLightInterface.OnShowCallback() {//监听显示回调
                    @Override
                    public void onShow(HightLightView hightLightView) {
                        Toast.makeText(requireContext(), "The HightLight view has been shown", Toast.LENGTH_SHORT).show();
                    }
                }).setOnNextCallback(new HighLightInterface.OnNextCallback() {
            @Override
            public void onNext(HightLightView hightLightView, View targetView, View tipView) {
                // targetView 目标按钮 tipView添加的提示布局 可以直接找到'我知道了'按钮添加监听事件等处理
                Toast.makeText(requireContext(), "The HightLight show next TipView，targetViewID:"+(targetView==null?null:targetView.getId())+",tipViewID:"+(tipView==null?null:tipView.getId()), Toast.LENGTH_SHORT).show();
            }
        });

        highLight.show();
    }

    private void showHighLightNormal(){
        mHighLight = new HighLight(requireContext())//
                .autoRemove(false)//设置背景点击高亮布局自动移除为false 默认为true
                .intercept(false)//设置拦截属性为false 高亮布局不影响后面布局的滑动效果 而且使下方点击回调失效
                .setClickCallback(new HighLight.OnClickCallback() {
                    @Override
                    public void onClick() {
                        Toast.makeText(requireContext(), "clicked and remove HightLight view by yourself", Toast.LENGTH_SHORT).show();
                        mHighLight.remove();
                    }
                })
//                .anchor(binding.getRoot())//如果是Activity上增加引导层，不需要设置anchor
                .addHighLight(binding.firstJumpButton,R.layout.guide_test,new OnLeftPosCallback(45),new RectLightShape())
                .addHighLight(binding.firstJumpButton,R.layout.guide_test,new OnBottomPosCallback(10),new OvalLightShape(5,5,20))
                .addHighLight(binding.firstJumpButton,R.layout.guide_test,new OnRightPosCallback(5),new CircleLightShape(0,0,0))
                .addHighLight(binding.firstJumpButton,R.layout.guide_test,new OnTopPosCallback(10),new CircleLightShape(0,0,0));
        mHighLight.show();

//        //added by isanwenyu@163.com 设置监听器只有最后一个添加到HightLightView的knownView响应了事件
//        //优化在布局中声明onClick方法 {@link #clickKnown(view)}响应所有R.id.iv_known的控件的点击事件
//        View decorLayout = mHightLight.getHightLightView();
//        ImageView knownView = (ImageView) decorLayout.findViewById(R.id.iv_known);
//        knownView.setOnClickListener(new View.OnClickListener()
//          {
//            @Override
//            public void onClick(View view) {
//                remove(null);
//            }
//        });
    }

    /**
     * 屏幕是否黑屏（完全变黑那种，屏幕变暗不算）
     *
     * @param context 上下文
     * @return 屏幕变黑，则返回true；屏幕变亮，则返回false
     */
    public static boolean isScreenOff(Context context) {
        KeyguardManager manager = (KeyguardManager) context.getSystemService(context.KEYGUARD_SERVICE);
        return manager.inKeyguardRestrictedInputMode();
    }
}
