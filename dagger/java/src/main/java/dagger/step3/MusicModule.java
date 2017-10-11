package dagger.step3;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.step3.music.MusicAnalytics;
import dagger.step3.music.MusicPlayer;
import dagger.step3.music.MusicSdk;

@Module
public class MusicModule {

    @Provides
    MusicPlayer musicPlayer() {
        return new MusicPlayer();
    }

    @Provides
    MusicAnalytics musicAnalytics() {
        return new MusicAnalytics();
    }

    @Singleton
    @Provides
    MusicSdk musicSdk(MusicPlayer musicPlayer, MusicAnalytics musicAnalytics) {
        return new MusicSdk(musicPlayer, musicAnalytics);
    }
}
