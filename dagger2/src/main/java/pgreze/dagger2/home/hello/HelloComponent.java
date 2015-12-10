package pgreze.dagger2.home.hello;

import dagger.Component;
import pgreze.dagger2.app.AppComponent;
import pgreze.dagger2.di.FragmentScope;

@FragmentScope
@Component(
        dependencies = AppComponent.class,
        modules = HelloModule.class
)
public interface HelloComponent {
    void inject(HelloFragment fragment);

    HelloFragment helloFragment();
    HelloPresenter helloPresenter();
}
