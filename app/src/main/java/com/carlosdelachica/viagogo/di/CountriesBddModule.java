package com.carlosdelachica.viagogo.di;

import com.carlosdelachica.viagogo.data.repository.datasources.bdd.Persistor;
import com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries.CountriesBddDataSourceImp;
import com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries.entities.BddCountry;
import com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries.mappers.BddCountryMapper;
import com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries.persistors.CountryPersistor;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.entities.abstractmappers.ListMapper;
import com.carlosdelachica.viagogo.repository.countries.datasources.CountriesBddDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        complete = false,
        library = true
)
public class CountriesBddModule {

    @Provides @Singleton
    CountriesBddDataSource provideCountriesBddDataSource(Persistor<BddCountry> persistor,
                                                   BddCountryMapper bddCountryMapper,
                                                   ListMapper<Country, BddCountry> countryListMapper) {
        return new CountriesBddDataSourceImp(persistor, countryListMapper);
    }

    @Provides @Singleton
    Persistor<BddCountry> provideBddCountryPersistor() {
        return new CountryPersistor();
    }

    @Provides @Singleton
    BddCountryMapper provideBddCountryMapper() {
        return new BddCountryMapper();
    }

    @Provides @Singleton
    ListMapper<Country, BddCountry> provideBddCountryListMapper(BddCountryMapper bddCountryMapper) {
        return new ListMapper<>(bddCountryMapper);
    }

}
