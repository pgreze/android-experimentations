package pgreze.dagger2.ui;

import dagger.Component;
import pgreze.dagger2.app.AppComponent;
import pgreze.dagger2.di.UILifecycleScope;
import pgreze.dagger2.ui.hello.HelloFragment;
import pgreze.dagger2.ui.player.PlayerFragment;

/**
 * Component used by {@link MainActivity} (see inject method).
 * <br/>
 * Be careful: allowed objects are only:
 * <ul>
 *     <li>parent components objects, {@link AppComponent} here</li>
 *     <li>objects with the same scope, {@link UILifecycleScope} here</li>
 *     <li>non scoped objects</li>
 * </ul>
 */
@UILifecycleScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
    void inject(HelloFragment fragment);
    void inject(PlayerFragment fragment);
}
