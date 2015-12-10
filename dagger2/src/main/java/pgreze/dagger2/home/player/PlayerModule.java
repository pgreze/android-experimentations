package pgreze.dagger2.home.player;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import pgreze.dagger2.di.FragmentScope;

@Module
public class PlayerModule {

    @Provides @FragmentScope public PlayerInteractor providePlayerInteractor(Context context) {
        return new PlayerInteractor(context);
    }
}
