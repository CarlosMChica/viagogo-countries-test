package com.carlosdelachica.viagogo.presentation.detail;

import com.carlosdelachica.viagogo.domain.EventBus;
import com.carlosdelachica.viagogo.domain.interactors.InteractorExecutor;
import com.carlosdelachica.viagogo.domain.interactors.countries.GetCountriesInRegionInteractor;
import com.carlosdelachica.viagogo.domain.interactors.countries.GetCountryInteractor;
import com.carlosdelachica.viagogo.domain.interactors.countries.events.GetCountriesInRegionEvent;
import com.carlosdelachica.viagogo.domain.interactors.countries.events.GetCountryEvent;
import com.carlosdelachica.viagogo.presentation.Presenter;

public class DetailPresenter extends Presenter {

    private final EventBus bus;
    private final InteractorExecutor interactorExecutor;
    private final GetCountryInteractor getCountryInteractor;
    private final GetCountriesInRegionInteractor getCountriesInRegionInteractor;
    private final DetailView detailView;

    public DetailPresenter(EventBus bus,
                           InteractorExecutor interactorExecutor,
                           GetCountryInteractor getCountryInteractor,
                           GetCountriesInRegionInteractor getCountriesInRegionInteractor,
                           DetailView detailView) {
        this.bus = bus;
        this.interactorExecutor = interactorExecutor;
        this.getCountryInteractor = getCountryInteractor;
        this.getCountriesInRegionInteractor = getCountriesInRegionInteractor;
        this.detailView = detailView;
    }

    public void uiReady(String alpha2Code) {
        detailView.refreshUiStarted();
        getCountryInteractor.setAlpha2Code(alpha2Code);
        interactorExecutor.execute(getCountryInteractor);
    }

    public void onRegionClicked(String region) {
        detailView.refreshUiStarted();
        getCountriesInRegionInteractor.setRegion(region);
        interactorExecutor.execute(getCountriesInRegionInteractor);
    }

    @Override public void onResume() {
        bus.register(this);
    }

    @Override public void onPause() {
        bus.unregister(this);
    }

    public void onEvent(GetCountryEvent event) {
        detailView.refreshUiFinished();
        if (event.getError() == null && event.getCountry() != null) {
            detailView.showCountryData(event.getCountry());
        } else {
            detailView.showGetCountryError();
        }
    }

    public void onEvent(GetCountriesInRegionEvent event) {
        detailView.refreshUiFinished();
        if (event.getError() == null && event.getCountries() != null) {
            detailView.showCountriesInRegionData(event.getCountries());
        } else {
            detailView.showGetCountryError();
        }
    }

}
