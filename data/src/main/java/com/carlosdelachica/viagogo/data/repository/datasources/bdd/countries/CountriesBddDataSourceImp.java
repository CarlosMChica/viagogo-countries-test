package com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries;

import com.activeandroid.query.Select;
import com.carlosdelachica.viagogo.data.repository.datasources.bdd.Persistor;
import com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries.entities.BddCountry;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.entities.abstractmappers.ListMapper;
import com.carlosdelachica.viagogo.repository.countries.datasources.CountriesBddDataSource;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.ObtainCountriesBddException;
import com.carlosdelachica.viagogo.repository.countries.datasources.exceptions.PersistCountriesBddException;

import java.sql.SQLException;
import java.util.List;

public class CountriesBddDataSourceImp implements CountriesBddDataSource {

    private final Persistor<BddCountry> persistor;
    private final ListMapper<Country, BddCountry> bddCountryListMapper;

    public CountriesBddDataSourceImp(Persistor<BddCountry> persistor,
                                     ListMapper<Country, BddCountry> bddCountryListMapper) {
        this.persistor = persistor;
        this.bddCountryListMapper = bddCountryListMapper;
    }

    @Override public List<Country> obtainAllCountries() throws ObtainCountriesBddException {
        try {
            List<BddCountry> bddCountryList = new Select()
                    .from(BddCountry.class)
                    .execute();
            return bddCountryListMapper.dataToModel(bddCountryList);
        } catch (Throwable e) {
            throw new ObtainCountriesBddException();
        }
    }

    @Override public void persistAllCountries(List<Country> countries) throws PersistCountriesBddException {
        try {
            List<BddCountry> bddCountries = bddCountryListMapper.modelToData(countries);
            for (BddCountry bddCountry : bddCountries) {
                persistor.persist(bddCountry);
            }
        } catch (SQLException e) {
            throw new PersistCountriesBddException();
        }
    }

}
