package com.carlosdelachica.viagogo.presentation.main;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;

import java.util.List;

public interface MainView {
    void showGetCountriesError();
    void refreshCountriesList(List<Country> countries);
    void refreshUiFinished();
    void refreshUiStarted();
}
