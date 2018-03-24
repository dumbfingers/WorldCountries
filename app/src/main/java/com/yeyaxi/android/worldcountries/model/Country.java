package com.yeyaxi.android.worldcountries.model;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("name")
    private String name;

    @SerializedName("nativeName")
    private String nativeName;

    @SerializedName("capital")
    private String capital;

    @SerializedName("region")
    private String region;

    @SerializedName("population")
    private Long population;

    @SerializedName("area")
    private Long area;

    @SerializedName("alpha2Code")
    private String code;

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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
