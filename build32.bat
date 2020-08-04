
SET PLATFORM_ANDROID32 = -platform Android-ARM -C android\AdMob\build\ .
SET PLATFORM_DEFAULT= -platform default -C default\ .

"%AIR_HOME%\bin\adt.bat" -package -target ane AdMob32.ane air\extension32.xml -swc air/AdMob/bin/AdMob.swc %PLATFORM_ANDROID32% %PLATFORM_DEFAULT%