/* Copyright (c) 2013 Pozirk Games
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
import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.InterstitialAd;

public class AdMobManager
  implements AdListener
{
  protected String _pubID;
  protected AdView _adView = null;
  protected AdSize _adSize = AdSize.BANNER;
  protected RelativeLayout _parentView;
  protected Activity _act;
  protected ExtensionContext _ctx;
  protected InterstitialAd _interstitial;

  public AdMobManager(String pubID, Activity act, ExtensionContext ctx)
  {
	  _act = act;
	  _ctx = ctx;

	  RelativeLayout layout = new RelativeLayout(_act);
	  _act.addContentView(layout, new ViewGroup.LayoutParams(-1, -1));

	  _pubID = pubID;

	  _parentView = layout;
  }
  
  public void show(int size, int halign, int valign, String testDevice)
  {
  	hide();
  	
  	switch(size)
  	{
  	case 1: _adSize = AdSize.BANNER; break; //set by default, but leave it here for reference
  	case 2: _adSize = AdSize.IAB_MRECT; break;
  	case 3: _adSize = AdSize.IAB_BANNER; break;
  	case 4: _adSize = AdSize.IAB_LEADERBOARD; break;
  	case 5: _adSize = AdSize.SMART_BANNER; break;
  	//case 6: _adSize = AdSize.IAB_WIDE_SKYSCRAPER; break;
  	}
  	
  	_adView = new AdView(_act, _adSize, _pubID);

  	_adView.setAdListener(this);
  	
  	AdRequest adRequest = new AdRequest();
  	if(testDevice != null)
  		adRequest.addTestDevice(testDevice);
  	
  	_adView.loadAd(adRequest);

  	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
  	params.addRule(halign, -1);
  	params.addRule(valign, -1);
  	_parentView.addView(_adView, params);
  }

  public void hide()
  {
  	if(_adView != null)
  	{
  		_adView.stopLoading();
  		_parentView.removeView(_adView);
  	}
  	
  	//_adView.destroy(); [update: ???]
  	_adView = null;
  }

  public void cacheInterstitial(String testDevice)
  {
  	_interstitial = new InterstitialAd(_act, _pubID);

  	AdRequest adRequest = new AdRequest();
  	if(testDevice != null)
  		adRequest.addTestDevice(testDevice);
  	
  	_interstitial.loadAd(adRequest);
  	_interstitial.setAdListener(this);
  }
  
  public void showInterstitial()
  {
  	if(_interstitial != null && _interstitial.isReady() == true)
  		_interstitial.show();
  }

  public void dispose()
  {
  	hide();
  	/*ViewGroup vg = (ViewGroup)_adView.getParent();
  	if (vg != null)
  		vg.removeView(this.adView);*/
  }

  public void onReceiveAd(Ad ad)
  {
  	if(_ctx != null)
    {
  		if(ad == _interstitial)
  			_ctx.dispatchStatusEventAsync("INTERSTITIAL_CACHE_OK", "");
  		else if(_adView != null)
      {
  			if(_adSize == AdSize.SMART_BANNER)
        {
  				AdSize testSize = AdSize.createAdSize(AdSize.SMART_BANNER, _ctx.getActivity());
  				_ctx.dispatchStatusEventAsync("AD_SHOW_OK", String.valueOf(testSize.getWidth()) + "x" + String.valueOf(testSize.getHeight()));
        }
        else
        	_ctx.dispatchStatusEventAsync("AD_SHOW_OK", String.valueOf(_adSize.getWidth()) + "x" + String.valueOf(_adSize.getHeight()));
      }
      else
      	_ctx.dispatchStatusEventAsync("AD_SHOW_FAIL", "Error! No AdView.");
    }
  }

  public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode error)
  {
  	if(_ctx != null)
    {
  		if(ad == _interstitial)
  			_ctx.dispatchStatusEventAsync("INTERSTITIAL_CACHE_FAIL", error.toString());
      else
      	_ctx.dispatchStatusEventAsync("AD_SHOW_FAIL", error.toString());
    }
  }

  public void onPresentScreen(Ad ad)
  {
  	if(_ctx != null)
    {
  		if(ad == _interstitial)
  			_ctx.dispatchStatusEventAsync("PRESENT_SCREEN", "Interstitial");
      else
      	_ctx.dispatchStatusEventAsync("PRESENT_SCREEN", "Ad");
    }
  }

  public void onDismissScreen(Ad ad)
  {
  	if(_ctx != null)
    {
  		if(ad == _interstitial)
  			_ctx.dispatchStatusEventAsync("INTERSTITIAL_CLOSED", "");
      else
      	_ctx.dispatchStatusEventAsync("DISMISS_SCREEN", "Ad");
    }
  }

  public void onLeaveApplication(Ad ad)
  {
  	if(_ctx != null)
  		_ctx.dispatchStatusEventAsync("LEAVE_APPLICATION", "");
  }
}