package com.carlosdelachica.viagogo.presentation.countries_translator;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;

public interface CountriesTranslator {
    String translate(Country country);
}
