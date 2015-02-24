package com.carlosdelachica.viagogo.di;

import com.carlosdelachica.viagogo.presentation.countries_sorter.CountriesSorter;
import com.carlosdelachica.viagogo.presentation.countries_sorter.CountriesSorterImp;
import com.carlosdelachica.viagogo.presentation.countries_translator.CountriesTranslator;
import com.carlosdelachica.viagogo.presentation.countries_translator.CountriesTranslatorImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        complete = false,
        library = true
)
public class UiModule {

    @Provides @Singleton
    CountriesSorter provideCountriesSorter(CountriesTranslator countriesTranslator) {
        return new CountriesSorterImp(countriesTranslator);
    }

    @Provides @Singleton
    CountriesTranslator provideCountriesTranslator() {
        return new CountriesTranslatorImp();
    }

}
