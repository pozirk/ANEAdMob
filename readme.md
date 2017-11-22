# News
2017-11-22: Updated to the latest version of Adobe AIR 27 and Google Play Services 11.0.4. Still working as expected, at least for me. :)

# About
ANEAdMob is an Adobe AIR native extension (ANE) for Android to show ads.<br />
Supported functionality:<br />
- show ad;<br />
- cache interstitial ad;<br />
- show interstitial ad;<br />
- hide ad;<br />
- listen tap, close, leave, etc. event.<br />

# Docs
Please, read docs and try ANE before asking any questions.<br />
https://developers.google.com/mobile-ads-sdk/<br />
http://help.adobe.com/en_US/air/extensions/index.html<br />


# Installation
Extension ID: com.pozirk.ads.AdMob<br />
Add "AdMob.ane" and "air\AdMob\bin\AdMob.swc" to your AIR project.<br />
Add the following lines to your AIR Aplication-app.xml file inside &lt;manifestAdditions&gt; section:<br />
<br />
&lt;uses-permission android:name="android.permission.INTERNET"/&gt;<br />
&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;<br />
&lt;application&gt;<br />
	&lt;meta-data android:name="com.google.android.gms.version" android:value="11020000" /&gt; &lt;!-- should be android:value="@integer/google_play_services_version" --&gt;
	&lt;activity android:name="com.google.android.gms.ads.AdActivity" android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"/&gt;<br />
&lt;/application&gt;<br />


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
_admob.init();

...

protected function onEvent(ae:AdEvent):void
{
	trace(ae.type+" "+ae._data);
}
//<


//showing smart-size ad at the bottom center side of the screen
_admob.show("AD_UNIT_ID", AdParams.SIZE_SMART_BANNER, AdParams.HALIGN_CENTER, AdParams.VALIGN_BOTTOM);

//hide ad
_admob.hide();

//caching and showing interstitial ad
_admob.cacheInterstitial("AD_UNIT_ID");
...
_admob.showInterstitial();
...
_admob.setVolume(vol); //set volume of the ad, 0-1 range, where 0 - mute, 1 - max volume.
```

# Games with AdMob
https://play.google.com/store/apps/details?id=air.com.pozirk.allinonesolitaire<br />
https://play.google.com/store/apps/details?id=air.com.pozirk.allinonemahjong<br />
https://play.google.com/store/apps/details?id=air.com.pozirk.allinonemahjong2<br />
https://play.google.com/store/apps/details?id=air.com.pozirk.allinonemahjong3<br />
In order to see the interstitial ad, you need to win/lose any game.<br />