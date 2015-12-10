package pgreze.hellodagger.home;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pgreze.hellodagger.app.AppModule;
import pgreze.hellodagger.home.player.PlayerModule;

@Module(
        complete = true, // Enable graph validation
        library = true,
        addsTo = AppModule.class, // Important for graph validation at compile time
        injects = HomeActivity.class,
        includes = PlayerModule.class // Valid because there is a no args constructor
)
public class HomeModule {

    private final HomeActivity homeActivity;

    public HomeModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides @Singleton public HomeActivity provideActivity() {
        return homeActivity;
    }
}
