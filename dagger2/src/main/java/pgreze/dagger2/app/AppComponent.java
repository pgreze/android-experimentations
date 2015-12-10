package pgreze.dagger2.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import pgreze.dagger2.analytics.AnalyticsManager;

@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {
    void inject(Dagger2App app);

    Context context();
    AnalyticsManager analyticsManager();
}
