package com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries.mappers;

import com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries.entities.BddCountry;
import com.carlosdelachica.viagogo.domain.entities.abstractmappers.Mapper;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;

public class BddCountryMapper implements Mapper<Country, BddCountry> {

    @Override public BddCountry modelToData(Country model) {
        if (model == null) {
            return null;
        }
        BddCountry data = new BddCountry();
        data.setAlpha2Code(model.getAlpha2Code());
        data.setAlpha3Code(model.getAlpha3Code());
        data.setArea(model.getArea());
        data.setCapital(model.getCapital());
        data.setDemonym(model.getDemonym());
        data.setGini(model.getGini());
        data.setName(model.getName());
        data.setNativeName(model.getNativeName());
        data.setPopulation(model.getPopulation());
        data.setRegion(model.getRegion());
        data.setRelevance(model.getRelevance());
        data.setSubregion(model.getSubregion());
        data.setFlagUrl(model.getFlagUrl());
        return data;
    }

    @Override public Country dataToModel(BddCountry data) {
        if (data == null) {
            return null;
        }
        Country model = new Country();
        model.setAlpha2Code(data.getAlpha2Code());
        model.setAlpha3Code(data.getAlpha3Code());
        model.setArea(data.getArea());
        model.setCapital(data.getCapital());
        model.setDemonym(data.getDemonym());
        model.setGini(data.getGini());
        model.setName(data.getName());
        model.setNativeName(data.getNativeName());
        model.setPopulation(data.getPopulation());
        model.setRegion(data.getRegion());
        model.setRelevance(data.getRelevance());
        model.setSubregion(data.getSubregion());
        model.setFlagUrl(data.getFlagUrl());
        return model;
    }

}
