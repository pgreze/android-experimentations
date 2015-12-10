package pgreze.dagger2.home.hello;

import dagger.Module;
import dagger.Provides;
import pgreze.dagger2.analytics.AnalyticsManager;
import pgreze.dagger2.di.FragmentScope;

@Module
public class HelloModule {

    private HelloFragment fragment;

    public HelloModule(HelloFragment fragment) {
        this.fragment = fragment;
    }

    @Provides @FragmentScope public HelloFragment provideFragment() {
        return fragment;
    }

    @Provides @FragmentScope public HelloPresenter providePresenter(
            HelloFragment helloFragment, AnalyticsManager analyticsManager) {
        return new HelloPresenter(fragment, analyticsManager);
    }
}
