del android\AdMob\build\libAdMob.jar

del AdMob.ane

xcopy android\AdMob\bin\classes android\AdMob\bin /S /Y /R

rd android\AdMob\bin\classes /S /Q

"c:\Program Files (x86)\Java\jdk1.7.0_13\bin\jar.exe" cvf android/AdMob/build/libAdMob.jar -C android/AdMob/bin .

SET PLATFORM_ANDROID= -platform Android-ARM -C android\AdMob\build\ .
SET PLATFORM_DEFAULT= -platform default -C default\ .

"c:\Program Files (x86)\Adobe\AIR3.6\bin\adt.bat" -package -target ane AdMob.ane air\extension.xml -swc air/AdMob/bin/AdMob.swc %PLATFORM_ANDROID% %PLATFORM_DEFAULT%