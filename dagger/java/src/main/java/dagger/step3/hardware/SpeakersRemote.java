package dagger.step3.hardware;

import dagger.step3.music.MusicSdk;

public class SpeakersRemote {

    private static final SpeakersRemote instance = new SpeakersRemote();

    public static SpeakersRemote getInstance() {
        return instance;
    }

    private MusicSdk musicSdk;

    private SpeakersRemote() {}

    public void init(MusicSdk musicSdk) {
        this.musicSdk = musicSdk;
    }

    public void play(Object track) {
        musicSdk.play(track);
    }
}
