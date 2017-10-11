package dagger.step2;

import javax.inject.Inject;

public class MusicSdk {

    MusicPlayer musicPlayer;
    MusicAnalytics musicAnalytics;

    @Inject
    public MusicSdk(MusicPlayer musicPlayer, MusicAnalytics musicAnalytics) {
        this.musicPlayer = musicPlayer;
        this.musicAnalytics = musicAnalytics;
    }

    public void play(Object track) {
        musicPlayer.play(track);
        musicAnalytics.track(track);
    }
}
