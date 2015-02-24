package com.carlosdelachica.viagogo.repository.countries.datasources;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.CountriesNetworkException;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.ObtainCountryNetworkException;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.UnknownObtainCountriesException;

import java.util.List;

public interface CountriesNetworkDataSource {

    public List<Country> obtainAllCountries() throws CountriesNetworkException, UnknownObtainCountriesException;
    Country obtainCountry(String alpha2Code) throws ObtainCountryNetworkException;
    public List<Country> obtainCountriesInRegion(String region) throws CountriesNetworkException, UnknownObtainCountriesException;

}
