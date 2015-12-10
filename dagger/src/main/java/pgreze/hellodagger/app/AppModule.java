package pgreze.hellodagger.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pgreze.hellodagger.analytics.AnalyticsModule;

@Module(
        complete = true, // Enable object graph validation
        injects = {
                HelloDaggerApp.class
        },
        includes = {
                AnalyticsModule.class
        }
)
public class AppModule {

    private HelloDaggerApp app;

    public AppModule(HelloDaggerApp app) {
        this.app = app;
    }

    @Provides @Singleton public Context provideAppContext() {
        return app;
    }
}
