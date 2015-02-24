package com.carlosdelachica.viagogo.presentation.countries_sorter;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.presentation.countries_translator.CountriesTranslator;

public class CountriesSorterImp implements CountriesSorter {

    private final CountriesTranslator countriesTranslator;

    public CountriesSorterImp(CountriesTranslator countriesTranslator) {
        this.countriesTranslator = countriesTranslator;
    }

    @Override
    public int sort(Country country1, Country country2) {
        String country1Name = countriesTranslator.translate(country1);
        String country2Name = countriesTranslator.translate(country2);
        return country1Name.compareToIgnoreCase(country2Name);
    }

}
