package com.carlosdelachica.viagogo;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.activeandroid.ActiveAndroid;

import dagger.ObjectGraph;
import timber.log.Timber;

public class App extends Application {

    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        initObjectGraph();
        initActiveAndroid();
        initTimber();
        initStrictMode();
    }

    private void initObjectGraph() {
        objectGraph = ObjectGraph.create(new AppModule(this));
        inject(this);
    }

    private void initActiveAndroid() {
        ActiveAndroid.initialize(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private void initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .penaltyLog()
                    .build());
        }
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.HollowTree {
        @Override
        public void i(String message, Object... args) {
            // TODO e.g., Crashlytics.log(String.format(message, args));
        }

        @Override
        public void i(Throwable t, String message, Object... args) {
            i(message, args); // Just add to the log.
        }

        @Override
        public void e(String message, Object... args) {
            i("ERROR: " + message, args); // Just add to the log.
        }

        @Override
        public void e(Throwable t, String message, Object... args) {
            e(message, args);
            // TODO e.g., Crashlytics.logException(t);
        }
    }

}
