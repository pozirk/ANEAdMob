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

package com.pozirk.ads.admob.context;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.pozirk.ads.admob.manager.AdMobManager;
import com.pozirk.ads.admob.function.CacheInterstitialFunction;
import com.pozirk.ads.admob.function.DisposeFunction;
import com.pozirk.ads.admob.function.HideFunction;
import com.pozirk.ads.admob.function.InitFunction;
import com.pozirk.ads.admob.function.SetVolumeFunction;
import com.pozirk.ads.admob.function.ShowFunction;
import com.pozirk.ads.admob.function.ShowInterstitialFunction;

import java.util.HashMap;
import java.util.Map;

public class ExtensionContext extends FREContext
{
  private static AdMobManager _adMobMan;

  public void dispose()
  {
  	_adMobMan.dispose();
  	_adMobMan = null;
  }

  public Map<String, FREFunction> getFunctions()
  {
  	Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();

  	functionMap.put("init", new InitFunction());
  	functionMap.put("show", new ShowFunction());
  	functionMap.put("hide", new HideFunction());
  	functionMap.put("cacheInterstitial", new CacheInterstitialFunction());
  	functionMap.put("showInterstitial", new ShowInterstitialFunction());
  	functionMap.put("dispose", new DisposeFunction());
  	functionMap.put("setVolume", new SetVolumeFunction());

  	return functionMap;
  }

	public AdMobManager getAdMobMan() {
		if(_adMobMan==null) {

//			try {
				_adMobMan = new AdMobManager(getActivity(), this);
//			}
//			catch(Exception e) {
//				dispatchStatusEventAsync("INIT_FAIL", e.getMessage());
//			}
		}
		return _adMobMan;
	}
}