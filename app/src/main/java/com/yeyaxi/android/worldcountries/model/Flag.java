package com.yeyaxi.android.worldcountries.model;

import com.yeyaxi.android.worldcountries.Params;

public class Flag {
    public static String getUri(String countryCode) {
        return Params.IMAGE_BASE_PATH + countryCode.toLowerCase() + ".gif";
    }
}
