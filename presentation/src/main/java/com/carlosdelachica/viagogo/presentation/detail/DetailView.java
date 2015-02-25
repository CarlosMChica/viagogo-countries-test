package com.carlosdelachica.viagogo.presentation.detail;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;

import java.util.List;

public interface DetailView {
    void showGetCountryError();
    void showCountryData(Country country);
    void showCountriesInRegionData(List<Country> countries);
    void refreshUiStarted();
    void refreshUiFinished();
}
