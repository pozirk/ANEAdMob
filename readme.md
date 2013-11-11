# About
ANEAdMob is an Adobe AIR native extension (ANE) for Android to show ads.<br />
Supported functionality:<br />
- show ad;<br />
- cache interstitial ad;<br />
- show interstitial ad;<br />
- hide ad;<br />
- listen tap, close, leave, etc. event.<br />
<br />
DOES ANYBODY WANT TO UPGRADE THIS ANE TO SUPPORT IOS?<br />
CONTACT ME OR JUST CONTRIBUTE YOUR MODIFICATIONS TO REPOSITORY.<br />

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
	&lt;activity android:name="com.google.ads.AdActivity" android:configChanges="keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize"/&gt;<br />
&lt;/application&gt;<br />


# Example
```actionscript
import com.pozirk.ads.admob.AdMob;
import com.pozirk.ads.admob.AdParams;
import com.pozirk.ads.admob.AdEvent;

//> initialization of AdMob
_admob = new AdMob();
_admob.addEventListener(AdEvent.INIT_OK, onEvent);
_admob.addEventListener(AdEvent.INIT_FAIL, onEvent);
_admob.addEventListener(AdEvent.AD_SHOW_OK, onEvent);
_admob.addEventListener(AdEvent.AD_SHOW_FAIL, onEvent);
_admob.addEventListener(AdEvent.PRESENT_SCREEN, onEvent);
_admob.addEventListener(AdEvent.DISMISS_SCREEN, onEvent);
_admob.addEventListener(AdEvent.LEAVE_APPLICATION, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_CACHE_OK, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_CACHE_OK, onEvent);
_admob.addEventListener(AdEvent.INTERSTITIAL_CLOSED, onEvent);
_admob.init("YOUR_PUBLISHER_ID");

...

protected function onEvent(ae:AdEvent):void
{
	trace(ae.type+" "+ae._data);
}
//<


//showing 468x60 ad at the bottom center side of the screen
_admob.show(AdParams.SIZE_IAB_BANNER, AdParams.HALIGN_CENTER, AdParams.VALIGN_BOTTOM);

//caching and showing interstitial ad
_admob.cacheInterstitial();
...
_admob.showInterstitial();
```

# Game with AdMob
https://play.google.com/store/apps/details?id=air.com.pozirk.allinonesolitaire<br />
In order to see the interstitial ad, you need to win/lose any game.<br />


# Misc
ANE is build with AIR3.6, in order to rebuild for another version do the following:<br />
- edit "air\extension.xml" and change 3.6 in very first line to any 3.X you need;<br />
- edit "build.bat" and in the very last line change path from AIR3.X SDK to any AIR3.X SDK you need;<br />
- execute "build.bat" to repack the ANE.<br />