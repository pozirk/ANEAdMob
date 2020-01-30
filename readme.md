# News
2020-01-29:
Updated to the latest version of Adobe AIR SDK 33 and Google Play Services 11.0.4.<br />
32-bit version works fine.<br />
64-bit ANE was tested by some good guy, and he told me it worked fine too. :)

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
&lt;![CDATA[&lt;manifest android:installLocation="auto"&gt;<br />
&lt;uses-permission android:name="android.permission.INTERNET"/&gt;<br />
&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;<br />
&lt;application&gt;<br />
&lt;meta-data android:name="com.google.android.gms.version" android:value="11020000" /&gt;<br />
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
_admob.init();

...

protected function onEvent(ae:AdEvent):void
{
	trace(ae.type+" "+ae._data);
}
//<


//showing smart-size ad at the bottom center side of the screen
_admob.show("AD_UNIT_ID", AdParams.SIZE_SMART_BANNER, AdParams.HALIGN_CENTER, AdParams.VALIGN_BOTTOM);

//hiding ad
_admob.hide();

//caching interstitial ad
_admob.cacheInterstitial("AD_UNIT_ID");
...
//showing interstitial ad, make sure it's cached first
_admob.showInterstitial();
...
//setting volume of the interstitial ad, can have sound, if it's video
_admob.setVolume(vol); //0-1 range, where 0 - mute, 1 - max volume (default, I guess).
```

# Games with AdMob
https://play.google.com/store/apps/details?id=air.com.pozirk.allinonesolitaire.old<br />
In order to see the interstitial ad, you need to win/lose any game.<br />
