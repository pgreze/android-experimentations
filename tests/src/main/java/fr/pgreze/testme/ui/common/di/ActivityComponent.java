package fr.pgreze.testme.ui.common.di;

import dagger.Component;
import fr.pgreze.testme.domain.app.di.AppComponent;
import fr.pgreze.testme.domain.common.di.UILifecycleScope;
import fr.pgreze.testme.ui.gist.PublicGistsFragment;

@UILifecycleScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(PublicGistsFragment fragment);
}
