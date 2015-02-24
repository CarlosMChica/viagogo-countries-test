package com.carlosdelachica.viagogo.domain.interactors.countries;

import com.carlosdelachica.viagogo.domain.EventBus;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.interactors.Interactor;
import com.carlosdelachica.viagogo.domain.interactors.countries.events.GetCountriesInRegionEvent;
import com.carlosdelachica.viagogo.domain.interactors.countries.exceptions.CantRetrieveCountriesException;
import com.carlosdelachica.viagogo.domain.repository.CountriesRepository;

import java.util.List;

public class GetCountriesInRegionInteractor implements Interactor {

    private final EventBus bus;
    private final CountriesRepository repository;
    private String region;

    public GetCountriesInRegionInteractor(EventBus bus, CountriesRepository repository) {
        this.bus = bus;
        this.repository = repository;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override public void execute() {
        GetCountriesInRegionEvent event = new GetCountriesInRegionEvent();
        try {
            List<Country> countries = repository.obtainCountriesInRegion(region);
            event.setCountries(countries);
        } catch (CantRetrieveCountriesException e) {
            event.setError(e);
        }
        bus.post(event);
    }
}
