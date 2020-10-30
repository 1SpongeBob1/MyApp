package com.demo.imeitext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.AdError;
import com.anythink.rewardvideo.api.ATRewardVideoAd;
import com.anythink.rewardvideo.api.ATRewardVideoListener;
import com.demo.imeitext.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    private FragmentSecondBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.secondJumpButton.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(R.id.firstFragment);
        });

        binding.secondAdButton.setOnClickListener(v->{
            //展示广告
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

    }
}
