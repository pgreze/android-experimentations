package pgreze.hellodagger.analytics;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        complete = false,
        library = true
)
public class AnalyticsModule {

    @Singleton @Provides
    public AnalyticsManager provideAnalyticsManager(Context context) {
        return new AnalyticsManager(context);
    }
}
