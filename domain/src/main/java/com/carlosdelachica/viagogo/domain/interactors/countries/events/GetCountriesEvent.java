package com.carlosdelachica.viagogo.domain.interactors.countries.events;

import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.interactors.BaseEvent;

import java.util.List;

public class GetCountriesEvent extends BaseEvent {

    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
