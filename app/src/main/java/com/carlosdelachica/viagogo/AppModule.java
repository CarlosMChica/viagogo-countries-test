package com.carlosdelachica.viagogo;

import android.app.Application;
import com.carlosdelachica.viagogo.di.UiModule;
import com.carlosdelachica.viagogo.di.DataModule;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(
        includes = {
                DataModule.class,
                UiModule.class
        },
        injects = App.class,
        library = true
)
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides @Singleton Application provideApplication() {
        return app;
    }

}
