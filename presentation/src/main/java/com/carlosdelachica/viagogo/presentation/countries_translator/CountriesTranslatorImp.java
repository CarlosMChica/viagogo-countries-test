package com.carlosdelachica.viagogo.presentation.countries_translator;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.entities.countries.Translations;

import java.util.Locale;

public class CountriesTranslatorImp implements CountriesTranslator{

    private Locale defaultLocale;

    public CountriesTranslatorImp() {
        this.defaultLocale = Locale.getDefault();
    }

    @Override
    public String translate(Country country) {
        String countryName;
        Translations countryTranslations = country.getTranslations();
            switch (defaultLocale.getLanguage()) {
                case "de":
                    countryName = countryTranslations.getDe();
                    break;
                case "es":
                    countryName = countryTranslations.getEs();
                    break;
                case "it":
                    countryName = countryTranslations.getIt();
                    break;
                case "ja":
                    countryName = countryTranslations.getJa();
                    break;
                case "fr":
                    countryName = countryTranslations.getFr();
                    break;
                default:
                    countryName = country.getName();
                    break;
            }
        if (countryName == null) {
            countryName = country.getName();
        }
        return countryName;
    }

}
