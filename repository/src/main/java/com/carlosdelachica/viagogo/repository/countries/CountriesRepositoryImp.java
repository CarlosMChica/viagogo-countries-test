package com.carlosdelachica.viagogo.repository.countries;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.interactors.countries.exceptions.CantRetrieveCountriesException;
import com.carlosdelachica.viagogo.domain.interactors.countries.exceptions.CantRetrieveCountryException;
import com.carlosdelachica.viagogo.domain.repository.CountriesRepository;
import com.carlosdelachica.viagogo.repository.countries.datasources.CountriesBddDataSource;
import com.carlosdelachica.viagogo.repository.countries.datasources.CountriesNetworkDataSource;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.CountriesNetworkException;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.ObtainCountryNetworkException;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.UnknownObtainCountriesException;

import java.util.List;

public class CountriesRepositoryImp implements CountriesRepository {

    private final CountriesNetworkDataSource networkDataSource;
    private final CountriesBddDataSource bddDataSource;

    public CountriesRepositoryImp(CountriesNetworkDataSource networkDataSource, CountriesBddDataSource bddDataSource) {
        this.networkDataSource = networkDataSource;
        this.bddDataSource = bddDataSource;
    }

    @Override
    public List<Country> refreshAllCountries() throws CantRetrieveCountriesException {
        List<Country> countries;
        try {
            countries = networkDataSource.obtainAllCountries();
        } catch (UnknownObtainCountriesException | CountriesNetworkException e) {
            throw new CantRetrieveCountriesException();
        }
        return countries;
    }

    @Override public List<Country> obtainAllCountries() throws CantRetrieveCountriesException {
        List<Country> countries;
        try {
            //TODO: Code to illustrate how to cache data to avoid requesting network operations (For instance a data base)
            //This is a sample, so new data is requested each time
            //Possible to cache data to avoid request new network countries
//            countries = bddDataSource.obtainAllCountries();
//            if (countries == null || countries.size() == 0) {
            countries = networkDataSource.obtainAllCountries();
//                bddDataSource.persistAllCountries(countries);
//            }
        } catch (UnknownObtainCountriesException | CountriesNetworkException e) {
            throw new CantRetrieveCountriesException();
        }
        return countries;
    }

    @Override
    public Country obtainCountry(String alpha2Code) throws CantRetrieveCountryException {
        try {
            return networkDataSource.obtainCountry(alpha2Code);
        } catch (ObtainCountryNetworkException e) {
            throw new CantRetrieveCountryException();
        }
    }

    @Override
    public List<Country> obtainCountriesInRegion(String region) throws CantRetrieveCountriesException {
        List<Country> countries;
        try {
            countries = networkDataSource.obtainCountriesInRegion(region);
        } catch (UnknownObtainCountriesException | CountriesNetworkException e) {
            throw new CantRetrieveCountriesException();
        }
        return countries;
    }

}
