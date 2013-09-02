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
		public static const AD_SHOW_OK:String = "AD_SHOW_OK";
		public static const AD_SHOW_FAIL:String = "AD_SHOW_FAIL";
		public static const PRESENT_SCREEN:String = "PRESENT_SCREEN"; //Called when an Activity is created in front of your app, presenting the user with a full-screen ad UI in response to their touching ad.
		public static const DISMISS_SCREEN:String = "DISMISS_SCREEN"; //Called when the full-screen Activity presented with onPresentScreen has been dismissed and control is returning to your app.
		public static const LEAVE_APPLICATION:String = "LEAVE_APPLICATION"; //Called when an Ad touch will launch a new application.
		public static const INTERSTITIAL_CACHE_OK:String = "INTERSTITIAL_CACHE_OK";
		public static const INTERSTITIAL_CACHE_FAIL:String = "INTERSTITIAL_CACHE_FAIL";
		public static const INTERSTITIAL_CLOSED:String = "INTERSTITIAL_CLOSED";
		
		public var _data:String; //extra info about event
		
		public function AdEvent(type:String, data:String = null)
		{
			super(type, false, false);
			_data = data;
		}
	}
}
