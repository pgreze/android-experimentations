package dagger.step1;

public class MusicSdk {

    MusicPlayer musicPlayer;
    MusicAnalytics musicAnalytics;

    public MusicSdk(MusicPlayer musicPlayer, MusicAnalytics musicAnalytics) {
        this.musicPlayer = musicPlayer;
        this.musicAnalytics = musicAnalytics;
    }

    public void play(Object track) {
        musicPlayer.play(track);
        musicAnalytics.track(track);
    }
}
