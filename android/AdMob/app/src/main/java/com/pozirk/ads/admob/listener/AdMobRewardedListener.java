package com.pozirk.ads.admob.listener;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.pozirk.ads.admob.manager.AdTypesSupportedEnum;
import com.pozirk.ads.admob.context.ExtensionContext;

public class AdMobRewardedListener extends AdMobListener implements RewardedVideoAdListener {


    public AdMobRewardedListener(ExtensionContext ctx)
    {
        super(ctx, AdTypesSupportedEnum.REWARDED);
    }


    @Override
    public void onRewardedVideoAdLoaded() {
        super.onAdLoaded();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        super.onAdOpened();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        super.onAdClosed();
    }


    @Override
    public void onRewardedVideoAdLeftApplication() {
        super.onAdLeftApplication();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        super.onAdFailedToLoad(i);
    }


    @Override
    public void onRewardedVideoStarted() {
        _ctx.dispatchStatusEventAsync(_who.getType()+"_STARTED", "");
    }


    @Override
    public void onRewarded(RewardItem rewardItem) {
        _ctx.dispatchStatusEventAsync(_who.getType()+"_REWARDED", rewardItem.getType()+"-"+rewardItem.getAmount());
    }



    @Override
    public void onRewardedVideoCompleted() {
        _ctx.dispatchStatusEventAsync(_who.getType()+"_COMPLETED", "");
    }
}
