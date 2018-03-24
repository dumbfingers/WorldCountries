package com.yeyaxi.android.worldcountries.model;

import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("iso639_1")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("nativeName")
    private String nativeName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
