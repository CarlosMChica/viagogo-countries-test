package com.carlosdelachica.viagogo.di;

import com.carlosdelachica.viagogo.data.RetrofitLog;
import com.carlosdelachica.viagogo.data.UserAgent;
import com.carlosdelachica.viagogo.data.repository.datasources.api.ApiNetworkError;
import com.carlosdelachica.viagogo.domain.repository.CountriesRepository;
import com.carlosdelachica.viagogo.repository.countries.CountriesRepositoryImp;
import com.carlosdelachica.viagogo.repository.countries.datasources.CountriesBddDataSource;
import com.carlosdelachica.viagogo.repository.countries.datasources.CountriesNetworkDataSource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module(
        includes = {
                CountriesApiModule.class,
                CountriesBddModule.class,
        },
        complete = false,
        library = true
)
public class RepositoryModule {

    @Provides @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .create();
    }

    @Provides @Singleton
    CountriesRepository provideCountriesRepository(CountriesNetworkDataSource networkDataSource,
                                               CountriesBddDataSource bddDataSource) {
        return new CountriesRepositoryImp(networkDataSource, bddDataSource);
    }

    @Provides @Singleton
    RestAdapter provideRestAdapter(@UserAgent final String userAgent,
                                   Endpoint endPoint,
                                   @RetrofitLog boolean logTraces,
                                   Gson gson) {
        return new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLogLevel(logTraces ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(new OkHttpClient()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade requestFacade) {
                        requestFacade.addHeader("User-Agent", userAgent);
                    }
                })
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        ApiNetworkError networkError = new ApiNetworkError();
                        networkError.setStackTrace(cause.getStackTrace());
                        return networkError;
                    }
                })
                .build();
    }

}
