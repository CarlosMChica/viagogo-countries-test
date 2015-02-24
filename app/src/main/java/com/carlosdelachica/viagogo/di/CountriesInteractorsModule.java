package com.carlosdelachica.viagogo.di;

import com.carlosdelachica.viagogo.domain.EventBus;
import com.carlosdelachica.viagogo.domain.interactors.countries.GetAllCountriesInteractor;
import com.carlosdelachica.viagogo.domain.interactors.countries.GetCountriesInRegionInteractor;
import com.carlosdelachica.viagogo.domain.interactors.countries.GetCountryInteractor;
import com.carlosdelachica.viagogo.domain.interactors.countries.RefreshAllCountriesInteractor;
import com.carlosdelachica.viagogo.domain.repository.CountriesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        complete = false,
        library = true
)
public class CountriesInteractorsModule {

    @Provides @Singleton
    GetAllCountriesInteractor provideGetAllCountriesInteractor(EventBus bus, CountriesRepository repository) {
        return new GetAllCountriesInteractor(bus, repository);
    }

    @Provides @Singleton
    GetCountryInteractor provideGetCountryInteractor(EventBus bus, CountriesRepository repository) {
        return new GetCountryInteractor(bus, repository);
    }

    @Provides @Singleton
    RefreshAllCountriesInteractor provideRefreshAllCountriesInteractor(EventBus bus, CountriesRepository repository) {
        return new RefreshAllCountriesInteractor(bus, repository);
    }

    @Provides @Singleton
    GetCountriesInRegionInteractor provideGetCountriesInRegionInteractor(EventBus bus, CountriesRepository repository) {
        return new GetCountriesInRegionInteractor(bus, repository);
    }

}
