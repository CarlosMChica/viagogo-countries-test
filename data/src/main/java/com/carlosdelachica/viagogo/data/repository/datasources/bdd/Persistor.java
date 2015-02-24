package com.carlosdelachica.viagogo.data.repository.datasources.bdd;

import java.sql.SQLException;

public interface Persistor<T> {
    void persist(T data) throws SQLException;
}
