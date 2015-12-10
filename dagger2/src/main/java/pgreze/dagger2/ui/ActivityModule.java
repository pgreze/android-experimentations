package pgreze.dagger2.ui;

import java.util.Random;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import pgreze.dagger2.di.UILifecycleScope;

@Module
public class ActivityModule {

    private final MainActivity activity;
    private final Random random = new Random();

    public ActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    /**
     * This provider is annotated with:
     * <br/><br/>
     * - @Named("ActivityScope"), this object will be returned for all @Named("ActivityScope") requests
     * <br/>
     * - @ActivityScope, an unique instance will be created for all @Named("ActivityScope") requests
     * @return a singleton
     * @see MainActivity
     */
    @UILifecycleScope @Named("ActivityScope")
    @Provides StringBuilder provideBuilderActivityScope() {
        return new StringBuilder("activity " + random.nextInt());
    }

    /**
     * Special provider for @Named("Unscoped") requests
     * @return an unscoped StringBuilder
     */
    @Named("Unscoped")
    @Provides StringBuilder provideBuilderUnscoped() {
        return new StringBuilder("Unscoped " + random.nextInt());
    }

    /** Default provider for all StringBuilder requests */
    @Provides StringBuilder provideBuilder() {
        return new StringBuilder().append(random.nextInt());
    }
}
