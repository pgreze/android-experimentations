package pgreze.dagger2.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import pgreze.dagger2.analytics.AnalyticsManager;
import pgreze.dagger2.di.HasComponent;

public class Dagger2App extends Application implements HasComponent<AppComponent> {

    public static Dagger2App getInstance(Context context) {
        return (Dagger2App) context.getApplicationContext();
    }

    @Inject AnalyticsManager analyticsManager;

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component().inject(this);
        analyticsManager.event("App Opened");
    }

    @Override
    public AppComponent component() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return component;
    }
}
