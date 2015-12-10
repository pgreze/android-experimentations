package pgreze.dagger2.app;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pgreze.dagger2.BuildConfig;

@Module
public class AppModule {

    private final Dagger2App app;

    public AppModule(Dagger2App app) {
        this.app = app;
    }

    /**
     * Mandatory @Provides method because we're injecting:
     * <ul>
     *     <li>An external object. Context is not defined in our project</li>
     *     <li>An object without access to its constructor. It's common with Android objects: App/Activity/Service</li>
     * </ul>
     * We haven't to annotate this method with @Singleton because it's only an accessor.
     * @return app context
     */
    @Provides public Context provideApplication() {
        return app;
    }

    /**
     * Example for an explicit @Provides method.
     * We're using @Singleton so eac instance will be the same
     * @param context provided by the previous method. Use this.app is not forbidden :)
     * @return single shared preferences for the app
     */
    @Singleton @Provides public SharedPreferences provideStringBuilder(Context context) {
        return context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    }
}
