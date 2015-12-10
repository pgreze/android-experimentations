package pgreze.dagger2.home.player;

import dagger.Component;
import pgreze.dagger2.app.AppComponent;
import pgreze.dagger2.di.FragmentScope;

@FragmentScope
@Component(
        dependencies = AppComponent.class,
        modules = PlayerModule.class
)
public interface PlayerComponent {
    void inject(PlayerFragment fragment);

    PlayerInteractor playerInteractor();
}
