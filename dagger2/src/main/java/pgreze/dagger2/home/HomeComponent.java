package pgreze.dagger2.home;

import dagger.Component;
import pgreze.dagger2.di.ActivityScope;

/**
 * Component used with HomeActivity (see inject method).
 * <br/>
 * Be careful: @ActivityScope indicates that the module is in @ActivityScope.<br/>
 * In other words, only @ActivityScope or non scoped providers are allowed here.
 */
@ActivityScope
@Component(
        modules = HomeModule.class
)
public interface HomeComponent {
    void inject(HomeActivity activity);
}
