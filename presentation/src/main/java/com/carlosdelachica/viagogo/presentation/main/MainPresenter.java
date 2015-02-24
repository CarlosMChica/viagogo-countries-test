package com.carlosdelachica.viagogo.presentation.main;

import com.carlosdelachica.viagogo.domain.EventBus;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.interactors.InteractorExecutor;
import com.carlosdelachica.viagogo.domain.interactors.countries.GetAllCountriesInteractor;
import com.carlosdelachica.viagogo.domain.interactors.countries.RefreshAllCountriesInteractor;
import com.carlosdelachica.viagogo.domain.interactors.countries.events.GetCountriesEvent;
import com.carlosdelachica.viagogo.presentation.Presenter;
import com.carlosdelachica.viagogo.presentation.countries_sorter.CountriesSorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainPresenter extends Presenter {

    private final EventBus bus;
    private final InteractorExecutor interactorExecutor;
    private final GetAllCountriesInteractor getAllCountriesInteractor;
    private final RefreshAllCountriesInteractor refreshAllCountriesInteractor;
    private final CountriesSorter countriesSorter;
    private final MainView mainView;
    private final Comparator<Country> comparator = new Comparator<Country>() {
        @Override
        public int compare(Country country1, Country country2) {
            return countriesSorter.sort(country1, country2);
        }
    };

    public MainPresenter(EventBus bus,
                         InteractorExecutor interactorExecutor,
                         GetAllCountriesInteractor getAllCountriesInteractor,
                         RefreshAllCountriesInteractor refreshAllCountriesInteractor,
                         CountriesSorter countriesSorter,
                         MainView mainView) {
        this.bus = bus;
        this.interactorExecutor = interactorExecutor;
        this.getAllCountriesInteractor = getAllCountriesInteractor;
        this.refreshAllCountriesInteractor = refreshAllCountriesInteractor;
        this.countriesSorter = countriesSorter;
        this.mainView = mainView;
    }

    @Override public void onResume() {
        bus.register(this);
    }

    public void uiReady() {
        interactorExecutor.execute(getAllCountriesInteractor);
    }

    @Override public void onPause() {
        bus.unregister(this);
    }

    public void onEvent(GetCountriesEvent event) {
        if (event.getError() == null) {
            showCountries(event);
        } else {
            mainView.showGetCountriesError();
        }
        mainView.refreshUiFinished();
    }

    private void showCountries(GetCountriesEvent event) {
        List<Country> countries = event.getCountries();
        sortCountries(countries);
        mainView.refreshCountriesList(countries);
    }

    private void sortCountries(List<Country> countries) {
        Collections.sort(countries, comparator);
    }

    public void onRefresh() {
        interactorExecutor.execute(refreshAllCountriesInteractor);
        mainView.refreshUiStarted();
    }

}
