package com.carlosdelachica.viagogo.repository.countries.datasources;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.ObtainCountriesBddException;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.ObtainCountryNetworkException;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.PersistCountriesBddException;

import java.util.List;

public interface CountriesBddDataSource {
    public List<Country> obtainAllCountries() throws ObtainCountriesBddException;
    void persistAllCountries(List<Country> countries) throws PersistCountriesBddException;
}
