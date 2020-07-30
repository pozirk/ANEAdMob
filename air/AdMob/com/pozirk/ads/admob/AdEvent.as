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
	import flash.events.Event;

	/**
	 * Ad events
	 * @author Pozirk Games (http://www.pozirk.com)
	 */
	public class AdEvent extends Event
	{
		public static const INIT_OK:String = "INIT_OK";
		public static const INIT_FAIL:String = "INIT_FAIL";
		public static const BANNER_SHOW_OK:String = "BANNER_SHOW_OK";
		public static const BANNER_SHOW_FAIL:String = "BANNER_SHOW_FAIL";
		public static const BANNER_LEFT_APP:String = "BANNER_LEFT_APP"; //Called when an Ad touch will launch a new application.
		public static const BANNER_OPENED:String = "BANNER_OPENED";
		public static const BANNER_CLOSED:String = "BANNER_CLOSED";
		public static const INTERSTITIAL_SHOW_OK:String = "INTERSTITIAL_SHOW_OK";
		public static const INTERSTITIAL_SHOW_FAIL:String = "INTERSTITIAL_SHOW_FAIL";
		public static const INTERSTITIAL_LEFT_APP:String = "INTERSTITIAL_LEFT_APP"; //Called when an Ad touch will launch a new application.
		public static const INTERSTITIAL_CACHE_OK:String = "INTERSTITIAL_CACHE_OK";
		public static const INTERSTITIAL_CACHE_FAIL:String = "INTERSTITIAL_CACHE_FAIL";
		public static const INTERSTITIAL_OPENED:String = "INTERSTITIAL_OPENED";
		public static const INTERSTITIAL_CLOSED:String = "INTERSTITIAL_CLOSED";
		public static const REWARDED_CACHE_OK:String = "REWARDED_CACHE_OK";		
		public static const REWARDED_OPENED:String = "REWARDED_OPENED";
		public static const REWARDED_CLOSED:String = "REWARDED_CLOSED";		
		public static const REWARDED_LEFT_APP:String = "REWARDED_LEFT_APP"; 
		public static const REWARDED_CACHE_FAIL:String = "REWARDED_CACHE_FAIL";		
		public static const REWARDED_STARTED:String = "REWARDED_STARTED"; 
		public static const REWARDED_REWARDED:String = "REWARDED_REWARDED"; //value rewardItem.getType()+"-"+rewardItem.getAmount()
		public static const REWARDED_COMPLETED:String = "REWARDED_COMPLETED";
		
		public var _data:String; //extra info about event
		
		public function AdEvent(type:String, data:String = null)
		{
			super(type, false, false);
			_data = data;
		}
	}
}
