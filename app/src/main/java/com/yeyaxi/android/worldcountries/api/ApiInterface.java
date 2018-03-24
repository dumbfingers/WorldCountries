package com.yeyaxi.android.worldcountries.api;


import com.yeyaxi.android.worldcountries.model.Country;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    String BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET("all?fields=name;alpha2Code")
    Observable<List<Country>> getAllCountries();

    @GET("alpha/{code}")
    Observable<Country> getCountry(@Path("code") String countryCode);
}
