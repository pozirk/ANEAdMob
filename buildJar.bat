SET SEVEN_ZIP="C:\Program Files\7-Zip\7z.exe"

del /f/q/s android\AdMob\build\temp

mkdir android\AdMob\build\temp

mkdir android\AdMob\app\build\outputs\aar\temp

%SEVEN_ZIP% x android\AdMob\app\build\outputs\aar\*.aar -oandroid\AdMob\app\build\outputs\aar\temp -y

copy android\AdMob\app\build\outputs\aar\*.jar android\AdMob\build\temp
copy android\AdMob\app\build\outputs\aar\libs\*.jar android\AdMob\build\temp

del android\AdMob\build\temp\FlashRuntimeExtensions.jar

%SEVEN_ZIP% x android\AdMob\build\temp\*.jar -oandroid\AdMob\build\temp -y

del android\AdMob\build\temp\*.jar

del android\AdMob\build\*.jar

%SEVEN_ZIP% a android\AdMob\build\libAdMob.jar android\AdMob\build\temp\*

del /f/q/s android\AdMob\build\temp

rmdir /q/s android\AdMob\build\temp

del /f/q/s android\AdMob\app\build\outputs\aar\temp

rmdir /q/s android\AdMob\app\build\outputs\aar\temp

