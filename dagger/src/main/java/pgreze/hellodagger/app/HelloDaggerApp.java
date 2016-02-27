package pgreze.hellodagger.app;

import android.app.Application;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;
import pgreze.hellodagger.analytics.AnalyticsManager;

public class HelloDaggerApp extends Application {

    private ObjectGraph objectGraph;
    @Inject AnalyticsManager analyticsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        objectGraph.inject(this);
        analyticsManager.event("app_opened");
    }

    private List<Object> getModules() {
        return Collections.<Object>singletonList(new AppModule(this));
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return objectGraph.plus(modules);
    }
}
