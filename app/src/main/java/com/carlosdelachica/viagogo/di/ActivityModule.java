package com.carlosdelachica.viagogo.di;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.carlosdelachica.viagogo.AppModule;
import com.carlosdelachica.viagogo.ui.errors.ErrorManager;
import com.carlosdelachica.viagogo.ui.errors.SnackbarErrorManagerImp;
import com.carlosdelachica.viagogo.ui.helper_util.HelperUtil;
import com.carlosdelachica.viagogo.ui.helper_util.HelperUtilImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        addsTo = AppModule.class,
        library = true
)
public class ActivityModule {

    private ActionBarActivity activity;

    public ActivityModule(ActionBarActivity activity) {
        this.activity = activity;
    }

    @Provides ActionBar provideActionBar() {
        return activity.getSupportActionBar();
    }

    @Provides Context provideContext() {
        return activity;
    }

    @Provides ActionBarActivity provideActivity() {
        return activity;
    }

    @Provides ErrorManager provideErrorManager() {
        return new SnackbarErrorManagerImp(activity);
    }

    @Provides @Singleton
    HelperUtil provideHelperUtil() {
        return new HelperUtilImpl(activity);
    }

}
