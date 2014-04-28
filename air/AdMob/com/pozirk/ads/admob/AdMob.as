/* Copyright (c) 2014 Pozirk Games
 * http://inside.pozirk.com
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

package com.pozirk.ads.admob
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.system.Capabilities;
	
	/**
	 * Main AdMob class
	 * @author Pozirk Games (http://www.pozirk.com)
	 */
	public class AdMob extends EventDispatcher
	{
		protected var _ctx:ExtensionContext;

		public function AdMob()
		{
			_ctx = ExtensionContext.createExtensionContext("com.pozirk.ads.AdMob", null);
			if(_ctx != null)
				_ctx.addEventListener(StatusEvent.STATUS, onStatus);
			else
				trace("Error! ANE was not properly added to your project.");
		}

		public function init():void
		{
			try
			{
				_ctx.call("init");
			}
			catch(err:Error)
			{
				var e:AdEvent = null;
				if(err.errorID == 3500)
					e = new AdEvent(AdEvent.INIT_FAIL, "Isn't it clear, that ANDROID Extension will NOT work on PC/Mac???");
				else
					e = new AdEvent(AdEvent.INIT_FAIL, "ANE was not properly added to your project.");
				this.dispatchEvent(e);
			}
		}
		
		/**
		 * Show ad
		 * @param	adID - Ad unit ID
		 * @param	size - one of the constants from AdParams
		 * @param	halign - left, center, right,  from AdParams
		 * @param	valign - from AdParams
		 * @param	testDevice - device ID, one of the  way to find it is to run ads in regular mode and find the following line in logcat: <To get test ads on this device, call adRequest.addTestDevice("XXX");>. Just pass XXX as testDevice.
		 */
		public function show(adID:String, size:int, halign:int, valign:int, testDevice:String = null):void
		{
			_ctx.call("show", adID, size, halign, valign, testDevice);
		}

		public function hide():void
		{
			_ctx.call("hide");
		}

		/**
		 * Cache interstitial ad, listen for AdEvent.INTERSTITIAL_CACHE_OK before showing it
		 * @param	adID - Ad unit ID
		 * @param	testDevice - device ID
		 */
		public function cacheInterstitial(adID:String, testDevice:String = null):void
		{
			_ctx.call("cacheInterstitial", adID, testDevice);
		}

		/**
		 * Show interstitial ad, if it is not cached yet, nothing will be shown
		 */
		public function showInterstitial():void
		{
			_ctx.call("showInterstitial");
		}
		
		protected function onStatus(event:StatusEvent):void
		{
			var e:AdEvent = null;
			//trace(event.code+event.level);
			switch(event.code)
			{
				case "INIT_OK":
				{
					e = new AdEvent(AdEvent.INIT_OK);
					break;
				}
				
				case "INIT_FAIL":
				{
					e = new AdEvent(AdEvent.INIT_FAIL, event.level);
					break;
				}
				
				case "BANNER_SHOW_OK":
				{
					e = new AdEvent(AdEvent.BANNER_SHOW_OK);
					break;
				}
				
				case "BANNER_SHOW_FAIL":
				{
					e = new AdEvent(AdEvent.BANNER_SHOW_FAIL, event.level);
					break;
				}
				
				case "BANNER_LEFT_APP":
				{
					e = new AdEvent(AdEvent.BANNER_LEFT_APP);
					break;
				}
				
				case "BANNER_OPENED":
				{
					e = new AdEvent(AdEvent.BANNER_OPENED, event.level);
					break;
				}
				
				case "BANNER_CLOSED":
				{
					e = new AdEvent(AdEvent.BANNER_CLOSED);
					break;
				}
				
				case "INTERSTITIAL_SHOW_OK":
				{
					e = new AdEvent(AdEvent.INTERSTITIAL_SHOW_OK);
					break;
				}
				
				case "INTERSTITIAL_SHOW_FAIL":
				{
					e = new AdEvent(AdEvent.INTERSTITIAL_SHOW_FAIL, event.level);
					break;
				}
				
				case "INTERSTITIAL_LEFT_APP":
				{
					e = new AdEvent(AdEvent.INTERSTITIAL_LEFT_APP);
					break;
				}
				
				case "INTERSTITIAL_CACHE_OK":
				{
					e = new AdEvent(AdEvent.INTERSTITIAL_CACHE_OK);
					break;
				}
				
				case "INTERSTITIAL_CACHE_FAIL":
				{
					e = new AdEvent(AdEvent.INTERSTITIAL_CACHE_FAIL, event.level);
					break;
				}
				
				case "INTERSTITIAL_OPENED":
				{
					e = new AdEvent(AdEvent.INTERSTITIAL_OPENED);
					break;
				}
				
				case "INTERSTITIAL_CLOSED":
				{
					e = new AdEvent(AdEvent.INTERSTITIAL_CLOSED);
					break;
				}
			}
			
			if(e != null)
				this.dispatchEvent(e);
		}

		public function dispose():void
		{
			_ctx.call("dispose");
			_ctx.dispose();
		}
	}
}
