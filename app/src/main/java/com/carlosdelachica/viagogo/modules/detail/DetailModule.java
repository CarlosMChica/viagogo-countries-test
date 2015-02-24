package com.carlosdelachica.viagogo.modules.detail;

import dagger.Module;
import dagger.Provides;
import com.carlosdelachica.viagogo.di.ActivityModule;
import com.carlosdelachica.viagogo.domain.EventBus;
import com.carlosdelachica.viagogo.domain.interactors.InteractorExecutor;
import com.carlosdelachica.viagogo.domain.interactors.countries.GetCountriesInRegionInteractor;
import com.carlosdelachica.viagogo.domain.interactors.countries.GetCountryInteractor;
import com.carlosdelachica.viagogo.presentation.detail.DetailPresenter;
import com.carlosdelachica.viagogo.presentation.detail.DetailView;

@Module(
        addsTo = ActivityModule.class,
        injects = DetailActivity.class,
        library = true)
public class DetailModule {

    private DetailView detailView;

    public DetailModule(DetailView detailView) {
        this.detailView = detailView;
    }

    @Provides DetailPresenter providePresenter(EventBus bus,
                                               InteractorExecutor interactorExecutor,
                                               GetCountryInteractor getCountryInteractor,
                                               GetCountriesInRegionInteractor getCountriesInRegionInteractor){
        return new DetailPresenter(bus,
                interactorExecutor,
                getCountryInteractor,
                getCountriesInRegionInteractor,
                detailView);
    }

}
