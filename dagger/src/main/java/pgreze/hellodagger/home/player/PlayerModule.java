package pgreze.hellodagger.home.player;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        complete = false,
        library = true,
        injects = PlayerFragment.class
)
public class PlayerModule {

    @Provides @Singleton public PlayerInteractor providePlayerInteractor(Context context) {
        return new PlayerInteractor(context);
    }
}
