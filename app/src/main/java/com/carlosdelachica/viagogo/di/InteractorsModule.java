package com.carlosdelachica.viagogo.di;

import dagger.Module;

@Module(
        includes = {
                CountriesInteractorsModule.class,
        },
        complete = false,
        library = true
)
public class InteractorsModule {

}
