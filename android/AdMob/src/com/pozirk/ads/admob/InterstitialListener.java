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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

public class InterstitialListener extends AdListener
{
	protected ExtensionContext _ctx = null;
	
	public InterstitialListener(ExtensionContext ctx)
	{
		_ctx = ctx;
	}
	
	 @Override
   public void onAdLoaded()
	 {
		 _ctx.dispatchStatusEventAsync("AD_SHOW_OK", "");
   }

   @Override
   public void onAdFailedToLoad(int errorCode)
   {
     String errorReason = "";
     switch(errorCode)
     {
       case AdRequest.ERROR_CODE_INTERNAL_ERROR:
         errorReason = "Internal error";
         break;
       case AdRequest.ERROR_CODE_INVALID_REQUEST:
         errorReason = "Invalid request";
         break;
       case AdRequest.ERROR_CODE_NETWORK_ERROR:
         errorReason = "Network Error";
         break;
       case AdRequest.ERROR_CODE_NO_FILL:
         errorReason = "No fill";
         break;
     }
    
     _ctx.dispatchStatusEventAsync("AD_SHOW_FAIL", errorReason);
   }

   @Override
   public void onAdOpened()
   {
  	 _ctx.dispatchStatusEventAsync("AD_OPENED", "So, you've just earned some money, huh?");
   }

   @Override
   public void onAdClosed()
   {
  	 _ctx.dispatchStatusEventAsync("AD_CLOSED", "");
   }
}