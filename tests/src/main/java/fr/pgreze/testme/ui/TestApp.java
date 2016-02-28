package fr.pgreze.testme.ui;

import android.app.Application;
import android.content.Context;

import fr.pgreze.testme.domain.app.di.AppComponent;
import fr.pgreze.testme.domain.app.di.AppModule;
import fr.pgreze.testme.domain.app.di.DaggerAppComponent;
import fr.pgreze.testme.domain.common.di.HasComponent;

public class TestApp extends Application implements HasComponent<AppComponent> {

    public static TestApp getInstance(Context context) {
        return (TestApp) context.getApplicationContext();
    }

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO
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
