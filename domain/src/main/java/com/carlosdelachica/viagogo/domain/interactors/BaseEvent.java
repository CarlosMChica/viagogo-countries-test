package com.carlosdelachica.viagogo.domain.interactors;

public class BaseEvent {

    private Throwable error;

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
