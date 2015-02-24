package com.carlosdelachica.viagogo.data.repository.datasources.api.countries.mappers;

import com.carlosdelachica.viagogo.data.repository.datasources.api.countries.entities.ApiTranslations;
import com.carlosdelachica.viagogo.domain.entities.abstractmappers.Mapper;
import com.carlosdelachica.viagogo.domain.entities.countries.Translations;

public class ApiTranslationsMapper implements Mapper<Translations, ApiTranslations> {

    @Override
    public ApiTranslations modelToData(Translations model) {
        if (model == null) {
            return null;
        }
        ApiTranslations apiTranslations = new ApiTranslations();
        apiTranslations.setDe(model.getDe());
        apiTranslations.setEs(model.getEs());
        apiTranslations.setFr(model.getFr());
        apiTranslations.setIt(model.getIt());
        apiTranslations.setJa(model.getJa());
        return apiTranslations;
    }

    @Override
    public Translations dataToModel(ApiTranslations data) {
        if (data == null) {
            return null;
        }

        Translations model = new Translations();
        model.setDe(data.getDe());
        model.setEs(data.getEs());
        model.setFr(data.getFr());
        model.setIt(data.getIt());
        model.setJa(data.getJa());
        return model;
    }

}
