package pgreze.dagger2.home;

import java.util.Random;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import pgreze.dagger2.di.ActivityScope;

@Module
public class HomeModule {

    private Random random = new Random();

    /**
     * This provider is annotated with:
     * <br/><br/>
     * - @Named("ActivityScope"), this object will be returned for all @Named("ActivityScope") requests
     * <br/>
     * - @ActivityScope, an unique instance will be created for all @Named("ActivityScope") requests
     * @return a singleton
     * @see HomeActivity
     */
    @ActivityScope @Named("ActivityScope") @Provides
    StringBuilder provideBuilderActivityScope() {
        return new StringBuilder("activity " + random.nextInt());
    }

    /**
     * Special provider for @Named("Unscoped") requests
     * @return an unscoped StringBuilder
     */
    @Named("Unscoped") @Provides StringBuilder provideBuilderUnscoped() {
        return new StringBuilder("Unscoped " + random.nextInt());
    }

    /** Default provider for all StringBuilder requests */
    @Provides StringBuilder provideBuilder() {
        return new StringBuilder().append(random.nextInt());
    }
}
