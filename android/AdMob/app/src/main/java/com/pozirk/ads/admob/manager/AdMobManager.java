/* Copyright (c) 2014 Pozirk Games
 * http://www.pozirk.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pozirk.ads.admob.manager;

import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.pozirk.ads.admob.context.ExtensionContext;
import com.pozirk.ads.admob.listener.AdMobListener;
import com.pozirk.ads.admob.listener.AdMobRewardedListener;

//import android.util.Log;

public class AdMobManager
{
	private AdView _adView = null;
	private AdSize _adSize = AdSize.BANNER;
	private RelativeLayout _parentView;
	private ExtensionContext _ctx;
	private InterstitialAd _interstitial;
	private RewardedVideoAd _rewardedVideoAd;
	private RelativeLayout.LayoutParams _params;

	public AdMobManager(ExtensionContext ctx)
	{
		_ctx = ctx;

		MobileAds.initialize(ctx.getActivity());

		RelativeLayout layout = new RelativeLayout(ctx.getActivity());
		ctx.getActivity().addContentView(layout, new ViewGroup.LayoutParams(-1, -1));

		_parentView = layout;
	}

	public void show(String adID, int size, /*int autoHW,*/ int halign, int valign, String testDevice)
	{
		hide();

		switch(size)
		{
		case 1: _adSize = AdSize.BANNER; break; //set by default, but leave it here for reference
		case 2: _adSize = AdSize.MEDIUM_RECTANGLE; break;
		case 3: _adSize = AdSize.FULL_BANNER; break;
		case 4: _adSize = AdSize.LEADERBOARD; break;
		case 5: _adSize = AdSize.SMART_BANNER; break;
		case 6: _adSize = AdSize.WIDE_SKYSCRAPER; break;
		}

		_adView = new AdView(_ctx.getActivity());
		_adView.setAdUnitId(adID);
		_adView.setAdSize(_adSize);

		AdRequest adRequest = createAdRequest(testDevice);

		_adView.setAdListener(new AdMobListener(_ctx, AdTypesSupportedEnum.BANNER));

		_params = new RelativeLayout.LayoutParams(-2, -2);
		_params.addRule(halign, -1);
		_params.addRule(valign, -1);

		_parentView.addView(_adView, _params);

		_adView.loadAd(adRequest);
	}

	/**
	* Required to fix this problem: https://groups.google.com/forum/#!topic/google-admob-ads-sdk/avwVXvBt_sM
	*/
	public void bannerOnTop()
	{
		if(_adView != null) {
			_parentView.bringToFront();
		}
	}

	public void hide()
	{
		if(_adView != null)
		{
			_adView.pause();
			_parentView.removeView(_adView);
			_adView.destroy();
		}

		_adView = null;
	}

	public void cacheInterstitial(String adID, String testDevice)
	{
		//Log.d("SOLITAIRE", "CACHE");

		_interstitial = new InterstitialAd(_ctx.getActivity());
		_interstitial.setAdUnitId(adID);

		AdRequest adRequest = createAdRequest(testDevice);

		_interstitial.loadAd(adRequest);
		_interstitial.setAdListener(new AdMobListener(_ctx, AdTypesSupportedEnum.INTERSTITIAL));
	}

	private AdRequest createAdRequest(String testDevice) {
		AdRequest adRequest = null;
		if(testDevice == null) //no test device
			adRequest = new AdRequest.Builder().build();
		else
			adRequest = new AdRequest.Builder().addTestDevice(testDevice).build();

		return adRequest;
	}

	public void showInterstitial()
	{
	//Log.d("SOLITAIRE", "SHOW");

		if(_interstitial != null && _interstitial.isLoaded()) {
			_interstitial.show();
		}
	}



	public void cacheRewarded(String adId, String testDevice)
	{
		_rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(_ctx.getActivity());
		_rewardedVideoAd.setRewardedVideoAdListener(new AdMobRewardedListener(_ctx));

		AdRequest adRequest = createAdRequest(testDevice);

		_rewardedVideoAd.loadAd(adId, adRequest);


	}


	public void showRewarded() {
		if (_rewardedVideoAd!=null && _rewardedVideoAd.isLoaded()) {
			_rewardedVideoAd.show();
		}
	}

	public void setVolume(double vol)
	{
		//Log.d("SOLITAIRE", "MUTE");
		MobileAds.setAppVolume((float)vol);
		//Log.d("SOLITAIRE MUTE", ""+vol);
	}

	public void dispose()
	{
		_rewardedVideoAd.destroy(_ctx.getActivity());
		hide();
		_adView.destroy();

	}
}