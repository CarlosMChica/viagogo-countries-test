package com.carlosdelachica.viagogo.data.repository.datasources.api.countries.mappers;

import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.entities.ApiCountry;
import com.carlosdelachica.viagogo.domain.entities.countries.Country;
import com.carlosdelachica.viagogo.domain.entities.abstractmappers.Mapper;

import java.util.Locale;

public class ApiCountryMapper implements Mapper<Country, ApiCountry> {

    private String imagesUrl;
    private ApiTranslationsMapper apiTranslationsMapper;

    public ApiCountryMapper(String imagesUrl, ApiTranslationsMapper apiTranslationsMapper) {
        this.imagesUrl = imagesUrl;
        this.apiTranslationsMapper = apiTranslationsMapper;
    }

    @Override public ApiCountry modelToData(Country model) {
        if (model == null) {
            return null;
        }
        ApiCountry data = new ApiCountry();
        data.setAlpha2Code(model.getAlpha2Code());
        data.setAlpha3Code(model.getAlpha3Code());
        data.setAltSpellings(model.getAltSpellings());
        data.setArea(model.getArea());
        data.setBorders(model.getBorders());
        data.setCallingCodes(model.getCallingCodes());
        data.setCapital(model.getCapital());
        data.setCurrencies(model.getCurrencies());
        data.setDemonym(model.getDemonym());
        data.setGini(model.getGini());
        data.setLatlng(model.getLatlng());
        data.setName(model.getName());
        data.setNativeName(model.getNativeName());
        data.setPopulation(model.getPopulation());
        data.setRegion(model.getRegion());
        data.setRelevance(model.getRelevance());
        data.setSubregion(model.getSubregion());
        data.setTimezones(model.getTimezones());
        data.setTopLevelDomain(model.getTopLevelDomain());
        data.setLanguages(model.getLanguages());
        data.setTranslations(apiTranslationsMapper.modelToData(model.getTranslations()));
        return data;
    }

    @Override public Country dataToModel(ApiCountry data) {
        if (data == null) {
            return null;
        }
        Country model = new Country();
        model.setAlpha2Code(data.getAlpha2Code());
        model.setAlpha3Code(data.getAlpha3Code());
        model.setAltSpellings(data.getAltSpellings());
        model.setArea(data.getArea());
        model.setBorders(data.getBorders());
        model.setCallingCodes(data.getCallingCodes());
        model.setCapital(data.getCapital());
        model.setCurrencies(data.getCurrencies());
        model.setDemonym(data.getDemonym());
        model.setGini(data.getGini());
        model.setLatlng(data.getLatlng());
        model.setName(data.getName());
        model.setNativeName(data.getNativeName());
        model.setPopulation(data.getPopulation());
        model.setRegion(data.getRegion());
        model.setRelevance(data.getRelevance());
        model.setSubregion(data.getSubregion());
        model.setTimezones(data.getTimezones());
        model.setTopLevelDomain(data.getTopLevelDomain());
        model.setLanguages(data.getLanguages());
        model.setTranslations(apiTranslationsMapper.dataToModel(data.getTranslations()));
        model.setFlagUrl(mapLetterCountryName(data));
        return model;
    }

    private String mapLetterCountryName(ApiCountry data) {
        return imagesUrl + data.getAlpha2Code().toLowerCase(Locale.getDefault()) + ".gif";
    }

}
