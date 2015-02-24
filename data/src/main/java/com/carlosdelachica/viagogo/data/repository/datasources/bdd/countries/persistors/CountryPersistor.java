package com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries.persistors;

import com.carlosdelachica.viagogo.data.repository.datasources.bdd.Persistor;
import com.carlosdelachica.viagogo.data.repository.datasources.bdd.countries.entities.BddCountry;

import java.sql.SQLException;

public class CountryPersistor implements Persistor<BddCountry> {

    public CountryPersistor() {
    }

    @Override public void persist(BddCountry data) throws SQLException {
        try {
            data.save();
        } catch (Throwable e) {
            throw new SQLException();
        }
    }

}
