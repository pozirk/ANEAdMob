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

package com.pozirk.ads.admob;

import android.app.Activity;
//import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AdMobManager
{
  protected AdView _adView = null;
  protected AdSize _adSize = AdSize.BANNER;
  protected RelativeLayout _parentView;
  protected Activity _act;
  protected ExtensionContext _ctx;
  protected InterstitialAd _interstitial;
  protected RelativeLayout.LayoutParams _params;
  protected int _bannerOnTop = 0;

  public AdMobManager(Activity act, ExtensionContext ctx)
  {
	  _act = act;
	  _ctx = ctx;

	  RelativeLayout layout = new RelativeLayout(_act);
	  _act.addContentView(layout, new ViewGroup.LayoutParams(-1, -1));

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
  	
  	_adView = new AdView(_act);
  	_adView.setAdUnitId(adID);
  	_adView.setAdSize(_adSize);
  	
  	AdRequest adRequest = null;
  	if(testDevice != null) //no test device
  		adRequest = new AdRequest.Builder().build();
  	else
  		adRequest = new AdRequest.Builder().addTestDevice(testDevice).build(); //eto pizdec
  	
  	_adView.setAdListener(new AdMobListener(_ctx, "BANNER"));
  	
  	_adView.loadAd(adRequest);
  	
  	_params = new RelativeLayout.LayoutParams(-2, -2);
  	_params.addRule(halign, -1);
  	_params.addRule(valign, -1);
  }
  
  /**
   * Required to fix this problem: https://groups.google.com/forum/#!topic/google-admob-ads-sdk/avwVXvBt_sM
   */
  public void bannerOnTop()
  {
  	if(_bannerOnTop == 0)
  	{
  		_parentView.addView(_adView, _params);
  		_bannerOnTop = 1;
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
  	
  	_bannerOnTop = 0;
  	_adView = null;
  }

  public void cacheInterstitial(String adID, String testDevice)
  {
  	_interstitial = new InterstitialAd(_act);
  	_interstitial.setAdUnitId(adID);

  	AdRequest adRequest = null;
  	if(testDevice != null) //no test device
  		adRequest = new AdRequest.Builder().build();
  	else
  		adRequest = new AdRequest.Builder().addTestDevice(testDevice).build(); //eto pizdec
  	
  	_interstitial.loadAd(adRequest);
  	_interstitial.setAdListener(new AdMobListener(_ctx, "INTERSTITIAL"));
  }
  
  public void showInterstitial()
  {
  	if(_interstitial != null && _interstitial.isLoaded() == true)
  		_interstitial.show();
  }

  public void dispose()
  {
  	hide();
  	_adView.destroy();
  }
}