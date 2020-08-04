SET PLATFORM_ANDROID64= -platform Android-ARM64 -C android\AdMob\build\ .
SET PLATFORM_DEFAULT= -platform default -C default\ .

"%AIR_HOME%\bin\adt.bat" -package -target ane AdMob64.ane air\extension64.xml -swc air/AdMob/bin/AdMob.swc %PLATFORM_ANDROID64% %PLATFORM_DEFAULT%