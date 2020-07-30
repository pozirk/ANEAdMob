package com.pozirk.ads.admob.manager;

public enum AdTypesSupportedEnum {
    BANNER("BANNER"),
    INTERSTITIAL("INTERSTITIAL"),
    REWARDED("REWARDED");

    private String type;

    AdTypesSupportedEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
