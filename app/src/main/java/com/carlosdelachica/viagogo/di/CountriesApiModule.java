package com.carlosdelachica.viagogo.di;

import com.carlosdelachica.viagogo.data.ImagesUrl;
import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.CountriesApiService;
import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.CountriesNetworkDataSourceImp;
import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.entities.ApiCountry;
import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.mappers.ApiCountryMapper;
import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.mappers.ApiTranslationsMapper;
import com.carlosdelachica.viagogo.domain.entities.abstractmappers.ListMapper;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.repository.countries.datasources.CountriesNetworkDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module(
        complete = false,
        library = true
)
public class CountriesApiModule {

    @Provides @Singleton
    CountriesApiService provideApiService(RestAdapter restAdapter) {
        return restAdapter
                .create(CountriesApiService.class);
    }

    @Provides @Singleton
    CountriesNetworkDataSource provideCountriesNetworkDataSource(CountriesApiService apiService,
                                                                 ApiCountryMapper mapper) {
        return new CountriesNetworkDataSourceImp(apiService, mapper);
    }

    @Provides @Singleton
    ApiCountryMapper provideApiCountryMapper(@ImagesUrl String imagesUrl,
                                             ApiTranslationsMapper apiTranslationsMapper) {
        return new ApiCountryMapper(imagesUrl, apiTranslationsMapper);
    }

    @Provides @Singleton
    ApiTranslationsMapper apiTranslationsMapper() {
        return new ApiTranslationsMapper();
    }

    @Provides @Singleton
    ListMapper<Country, ApiCountry> countryApiCountryListMapper(ApiCountryMapper apiCountryMapper) {
        return new ListMapper<>(apiCountryMapper);
    }

}
