package pgreze.dagger2.app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Dagger2App app;

    public AppModule(Dagger2App app) {
        this.app = app;
    }

    @Provides public Context provideApplication() {
        return app;
    }
}
