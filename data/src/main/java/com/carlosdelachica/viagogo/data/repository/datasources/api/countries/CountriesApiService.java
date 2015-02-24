package com.carlosdelachica.viagogo.data.repository.datasources.api.countries;

import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.entities.ApiCountry;

import java.util.List;

import retrofit.http.EncodedPath;
import retrofit.http.GET;
import retrofit.http.Path;

public interface CountriesApiService {

    @GET("/all")
    List<ApiCountry> getAllCountries();

    @GET("/alpha/{alpha2Code}")
    ApiCountry getCountry(@Path("alpha2Code") String alpha2Code);

    @GET("/region/{region}")
    List<ApiCountry> getCountriesInRegion(@Path("region") String region);

}
