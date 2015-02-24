package com.carlosdelachica.viagogo.domain.repository;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.interactors.countries.exceptions.CantRetrieveCountriesException;
import com.carlosdelachica.viagogo.domain.interactors.countries.exceptions.CantRetrieveCountryException;

import java.util.List;

public interface CountriesRepository {
    List<Country> refreshAllCountries() throws CantRetrieveCountriesException;
    List<Country> obtainAllCountries() throws CantRetrieveCountriesException;
    Country obtainCountry(String alpha2Code) throws CantRetrieveCountryException;
    List<Country> obtainCountriesInRegion(String region) throws CantRetrieveCountriesException;
}
