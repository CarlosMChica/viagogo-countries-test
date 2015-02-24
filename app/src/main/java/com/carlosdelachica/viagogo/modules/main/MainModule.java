package com.carlosdelachica.viagogo.modules.main;

import com.carlosdelachica.viagogo.di.ActivityModule;
import com.carlosdelachica.viagogo.domain.EventBus;
import com.carlosdelachica.viagogo.domain.interactors.InteractorExecutor;
import com.carlosdelachica.viagogo.domain.interactors.countries.GetAllCountriesInteractor;
import com.carlosdelachica.viagogo.domain.interactors.countries.RefreshAllCountriesInteractor;
import com.carlosdelachica.viagogo.presentation.countries_sorter.CountriesSorter;
import com.carlosdelachica.viagogo.presentation.main.MainPresenter;
import com.carlosdelachica.viagogo.presentation.main.MainView;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(
        addsTo = ActivityModule.class,
        injects = MainActivity.class,
        library = true)
public class MainModule {

    private MainView mainView;

    public MainModule(MainView mainView) {
        this.mainView = mainView;
    }

    @Provides @Singleton MainPresenter provideMainPresenter(EventBus bus,
                                                            InteractorExecutor interactorExecutor,
                                                            GetAllCountriesInteractor getAllCountriesInteractor,
                                                            RefreshAllCountriesInteractor refreshAllCountriesInteractor,
                                                            CountriesSorter countriesSorter) {
        return new MainPresenter(bus,
                interactorExecutor,
                getAllCountriesInteractor,
                refreshAllCountriesInteractor,
                countriesSorter,
                mainView);
    }

}
