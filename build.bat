
SET PLATFORM_ANDROID= -platform Android-ARM -C android\AdMob\build\ .
SET PLATFORM_DEFAULT= -platform default -C default\ .

"%AIR_HOME%\bin\adt.bat" -package -target ane AdMob.ane air\extension.xml -swc air/AdMob/bin/AdMob.swc %PLATFORM_ANDROID% %PLATFORM_DEFAULT%