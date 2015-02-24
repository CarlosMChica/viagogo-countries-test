package com.carlosdelachica.viagogo.di;

import android.app.Application;
import android.os.Build;

import com.carlosdelachica.viagogo.BuildConfig;
import com.carlosdelachica.viagogo.data.ImagesUrl;
import com.carlosdelachica.viagogo.data.PicassoLog;
import com.carlosdelachica.viagogo.data.RetrofitLog;
import com.carlosdelachica.viagogo.data.UserAgent;
import com.carlosdelachica.viagogo.domain.EventBus;
import com.carlosdelachica.viagogo.domain.EventBusImp;
import com.carlosdelachica.viagogo.domain.InteractorExecutorImp;
import com.carlosdelachica.viagogo.domain.interactors.InteractorExecutor;
import com.carlosdelachica.viagogo.ui.imageloader.ImageLoader;
import com.carlosdelachica.viagogo.ui.imageloader.ImageLoaderImp;
import com.path.android.jobqueue.JobManager;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;

@Module(
        includes = {
                InteractorsModule.class,
                RepositoryModule.class,
        },
        complete = false,
        library = true
)
public class DataModule {

    @Provides @Singleton Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(BuildConfig.API_URL);
    }

    @Provides @Singleton @ImagesUrl String provideImagesUrl() {
        return BuildConfig.IMAGES_URL;
    }

    @Provides @Singleton @RetrofitLog boolean provideRetrofitLog() {
        return BuildConfig.RETROFIT_LOG;
    }

    @Provides @Singleton @PicassoLog boolean providePicassoLog() {
        return BuildConfig.PICASSO_LOG;
    }

    @Provides @Singleton @UserAgent String provideUserAgent() {
        return String.format("Sample-Android;%s;%s;%s;%d;", Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE, BuildConfig.VERSION_CODE);
    }

    @Provides @Singleton EventBus provideEventBus() {
        return new EventBusImp();
    }

    @Provides @Singleton JobManager provideJobManager(Application app) {
        return new JobManager(app);
    }

    @Provides @Singleton InteractorExecutor provideInteractorExecutor(JobManager jobManager, EventBus bus) {
        return new InteractorExecutorImp(jobManager, bus);
    }

    @Provides @Singleton Picasso providePicasso(Application app,
                                                @PicassoLog boolean picassoLog) {
        Picasso picasso = Picasso.with(app);
        picasso.setLoggingEnabled(picassoLog);
        return picasso;
    }

    @Provides @Singleton ImageLoader provideImageLoader(Picasso picasso) {
        return new ImageLoaderImp(picasso);
    }

}
