package com.carlosdelachica.viagogo.domain.interactors.countries.events;

import com.carlosdelachica.viagogo.domain.interactors.BaseEvent;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;

public class GetCountryEvent extends BaseEvent {

    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
