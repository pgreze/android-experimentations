package pgreze.dagger2.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import pgreze.dagger2.analytics.AnalyticsManager;

public class Dagger2App extends Application {

    public static Dagger2App getInstance(Context context) {
        return (Dagger2App) context.getApplicationContext();
    }

    private AppComponent component;

    @Inject AnalyticsManager analyticsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
        analyticsManager.event("app_opened");
    }

    private void setupGraph() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }
}
