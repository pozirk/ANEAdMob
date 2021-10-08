# News
2020-08-03
- Updated to the last version 19.3.0.<br />
- Batch file to build ANE 32bits, 64 or 32&64bit versions as 1 ANE<br/>
Big thanks to [@luc4smoreira](https://github.com/luc4smoreira) for this update!<br />

2020-07-30
- Rewarded videos <br/>
- Support for AndroidStudio with Gradle <br/>
- Batch file to build SWC and JAR <br/>
- Updated to the latest version of Adobe AIR SDK 33 and Google Play Services 16.0.0.<br />
Big thanks to [@luc4smoreira](https://github.com/luc4smoreira) for this update!<br />

2020-01-29
- Updated to the latest version of Adobe AIR SDK 33 and Google Play Services 11.0.4.<br />
- 32-bit version works fine.<br />
- 64-bit ANE was tested by some good guy, and he told me it worked fine too. :)

# About
ANEAdMob is an Adobe AIR native extension (ANE) for Android to show ads without Firebase.<br />
Supported functionality:<br />
- show ad;<br />
- cache interstitial ad;<br />
- cache rewarded video ad;
- show interstitial ad;<br />
- show rewarded video ad;<br />
- hide ad;<br />
- listen tap, close, leave, rewarded, failed, etc. event.<br />

# Docs
Please, read docs and try ANE before asking any questions.<br />
https://developers.google.com/mobile-ads-sdk/<br />
http://help.adobe.com/en_US/air/extensions/index.html<br />


# Installation
Extension ID: com.pozirk.ads.AdMob<br />
Add "AdMob.ane" and "air\AdMob\bin\AdMob.swc" to your AIR project.<br />
Add the following lines to your AIR Aplication-app.xml file inside &lt;manifestAdditions&gt; section:<br />
Add your AdMob App ID to the value of tag with name com.google.android.gms.ads.APPLICATION_ID, as shown below. <br/>

<br />
&lt;![CDATA[&lt;manifest android:installLocation="auto"&gt;<br />
&lt;uses-permission android:name="android.permission.INTERNET"/&gt;<br />
&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;<br />


&lt;meta-data android:name="com.google.android.gms.ads.APPLICATION_ID" android:value="ca-app-pub-xxxxxxxxxxxxxxxx~yyyyyyyyyy" /&gt;

&lt;application&gt;<br />
&lt;meta-data android:name="com.google.android.gms.version" android:value="12451000" /&gt;<br />
&lt;activity android:name="com.google.android.gms.ads.AdActivity" android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"/&gt;<br />
&lt;/application&gt;<br />
&lt;/manifest>]]&gt;


# Example
```actionscript
import com.pozirk.ads.admob.AdMob;
import com.pozirk.ads.admob.AdParams;
import com.pozirk.ads.admob.AdEvent;

...

protected var _admob:AdMob = new AdMob();

...

//> initialization of AdMob
_admob.addEventListener(AdEvent.INIT_OK, onEvent);
_admob.addEventListener(AdEvent.INIT_FAIL, onEvent);
_admob.addEventListener(AdEvent.BANNER_SHOW_OK, onEvent);
_admob.addEventListener(AdEvent.BANNER_SHOW_FAIL, onEvent);
_admob.addEventListener(AdEvent.BANNER_LEFT_APP, onEvent);
_admob.addEventListener(AdEvent.BANNER_OPENED, onEvent);
_admob.addEventListener(AdEvent.BANNER_CLOSED, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_SHOW_OK, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_SHOW_FAIL, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_CACHE_OK, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_CACHE_FAIL, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_LEFT_APP, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_OPENED, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_CLOSED, onEvent);
_admob.addEventListener(AdEvent.REWARDED_CACHE_FAIL, onEvent);
_admob.addEventListener(AdEvent.REWARDED_CACHE_OK, onEvent);
_admob.addEventListener(AdEvent.REWARDED_CLOSED, onEvent);
_admob.addEventListener(AdEvent.REWARDED_COMPLETED, onEvent);
_admob.addEventListener(AdEvent.REWARDED_LEFT_APP, onEvent);
_admob.addEventListener(AdEvent.REWARDED_OPENED, onEvent);
_admob.addEventListener(AdEvent.REWARDED_REWARDED, onEvent);
_admob.addEventListener(AdEvent.REWARDED_STARTED, onEvent);
_admob.init();

...

protected function onEvent(ae:AdEvent):void
{
	trace(ae.type+" "+ae._data);
}
//<


//showing smart-size ad at the bottom center side of the screen
//Admob official test ad unit for  banner "ca-app-pub-3940256099942544/6300978111"
_admob.show("AD_UNIT_ID", AdParams.SIZE_SMART_BANNER, AdParams.HALIGN_CENTER, AdParams.VALIGN_BOTTOM); 

//hiding ad
_admob.hide();

//caching interstitial ad
//Official test ad unit for interstitial "ca-app-pub-3940256099942544/1033173712"
_admob.cacheInterstitial("AD_UNIT_ID"); 
...
//showing interstitial ad, make sure it's cached first
_admob.showInterstitial();
...


//caching rewarded ad
//Official test ad unit for rewarded "ca-app-pub-3940256099942544/5224354917"
_admob.cacheRewarded("AD_UNIT_ID");  
...
//showing rewarded ad, make sure it's cached first
_admob.showRewarded();
...

//setting volume of the interstitial ad, can have sound, if it's video
_admob.setVolume(vol); //0-1 range, where 0 - mute, 1 - max volume (default, I guess).
```


# Testing
Ads might not show in debug version of the app or for new apps, that are not yet on Google Play or just created ad unit.<br />
Always try test mode first by passing your device id as last parameter to show/cacheInterstitial function.<br />
One of the ways to find device id is to run ads in regular mode and find the following line in logs: <To get test ads on this device, call adRequest.addTestDevice("XXX");>.
XXX is your device id.<br />
"No fill" or "Failed to load ad: 3" in logs most likely means that ads are working fine, but there are no ads for real, or admob doesn't want to provide any for some reason.<br />
Listen to all the events while debuging to get better idea what is going on, when something is not working.
