package com.carlosdelachica.viagogo.domain.interactors.countries;

import com.carlosdelachica.viagogo.domain.EventBus;
import com.carlosdelachica.viagogo.domain.interactors.Interactor;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.interactors.countries.events.GetCountriesEvent;
import com.carlosdelachica.viagogo.domain.interactors.countries.exceptions.CantRetrieveCountriesException;
import com.carlosdelachica.viagogo.domain.repository.CountriesRepository;

import java.util.List;

public class GetAllCountriesInteractor implements Interactor {

    private final EventBus bus;
    private final CountriesRepository repository;

    public GetAllCountriesInteractor(EventBus bus, CountriesRepository repository) {
        this.bus = bus;
        this.repository = repository;
    }

    @Override public void execute() {
        GetCountriesEvent event = new GetCountriesEvent();
        try {
            List<Country> countries = repository.obtainAllCountries();
            event.setCountries(countries);
        } catch (CantRetrieveCountriesException e) {
            event.setError(e);
        }
        bus.post(event);
    }

}
