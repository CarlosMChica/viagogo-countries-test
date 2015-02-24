package com.carlosdelachica.viagogo.data.repository.datasources.api.countries;

import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.entities.ApiCountry;
import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.mappers.ApiCountryMapper;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.repository.countries.datasources.CountriesNetworkDataSource;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.CountriesNetworkException;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.ObtainCountryNetworkException;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.UnknownObtainCountriesException;

import java.util.ArrayList;
import java.util.List;

public class CountriesNetworkDataSourceImp implements CountriesNetworkDataSource {

    private final CountriesApiService apiService;
    private final ApiCountryMapper mapper;

    public CountriesNetworkDataSourceImp(CountriesApiService apiService, ApiCountryMapper mapper) {
        this.apiService = apiService;
        this.mapper = mapper;
    }

    @Override
    public List<Country> obtainAllCountries() throws CountriesNetworkException {
        try {
            List<ApiCountry> apiCountries = apiService.getAllCountries();
            List<Country> countries = new ArrayList<>();
            if (apiCountries != null) {
                for (ApiCountry apiCountry : apiCountries) {
                    countries.add(mapper.dataToModel(apiCountry));
                }
            }
            return countries;
        } catch (Throwable e) {
            throw new CountriesNetworkException();
        }
    }

    @Override
    public Country obtainCountry(String alpha2Code) throws ObtainCountryNetworkException {
        try {
            ApiCountry country = apiService.getCountry(alpha2Code);
            return mapper.dataToModel(country);
        } catch (Throwable e) {
            throw new ObtainCountryNetworkException();
        }
    }

    @Override
    public List<Country> obtainCountriesInRegion(String region) throws CountriesNetworkException, UnknownObtainCountriesException {
        try {
            List<ApiCountry> apiCountries = apiService.getCountriesInRegion(region);
            List<Country> countries = new ArrayList<>();
            if (apiCountries != null) {
                for (ApiCountry apiCountry : apiCountries) {
                    countries.add(mapper.dataToModel(apiCountry));
                }
            }
            return countries;
        } catch (Throwable e) {
            throw new CountriesNetworkException();
        }
    }

}
