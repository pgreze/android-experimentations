package pgreze.hellodagger.home.hello;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pgreze.hellodagger.analytics.AnalyticsManager;

@Module(
        complete = false,
        library = true,
        injects = HelloFragment.class
)
public class HelloModule {

    private HelloFragment fragment;

    public HelloModule(HelloFragment fragment) {
        this.fragment = fragment;
    }

    @Provides @Singleton public HelloFragment provideFragment() {
        return fragment;
    }

    @Provides @Singleton public HelloPresenter providePresenter(
            HelloFragment helloFragment, AnalyticsManager analyticsManager) {
        return new HelloPresenter(fragment, analyticsManager);
    }
}
