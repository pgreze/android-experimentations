package pgreze.dagger2.app;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import pgreze.dagger2.analytics.AnalyticsManager;

/**
 * App component.
 * With @Singleton annotation, all @Singleton objects will be global to the app.
 * @see pgreze.dagger2.di.UILifecycleScope for activity/service single and lifecycle related
 */
@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {
    void inject(Dagger2App app);

    // We have to manually defined these accessors in order to be used by other components

    Context context();
    AnalyticsManager analyticsManager();
}
