package com.carlosdelachica.viagogo.domain.interactors.countries;

import com.carlosdelachica.viagogo.domain.EventBus;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.interactors.Interactor;
import com.carlosdelachica.viagogo.domain.interactors.countries.events.GetCountryEvent;
import com.carlosdelachica.viagogo.domain.interactors.countries.exceptions.CantRetrieveCountryException;
import com.carlosdelachica.viagogo.domain.repository.CountriesRepository;

public class GetCountryInteractor implements Interactor {

    private final EventBus bus;
    private final CountriesRepository repository;
    private String alpha2Code;

    public GetCountryInteractor(EventBus bus, CountriesRepository repository) {
        this.bus = bus;
        this.repository = repository;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    @Override public void execute() {
        GetCountryEvent event = new GetCountryEvent();
        try {
            Country country = repository.obtainCountry(alpha2Code);
            event.setCountry(country);
        } catch (CantRetrieveCountryException e) {
            event.setError(e);
        }
        bus.post(event);
    }

}
