package dagger.step3.hardware;

import dagger.step3.music.MusicSdk;

public class SpeakerRight {

    private static final SpeakerRight instance = new SpeakerRight();

    public static SpeakerRight getInstance() {
        return instance;
    }

    private MusicSdk musicSdk;

    private SpeakerRight() {}

    public void init(MusicSdk musicSdk) {
        this.musicSdk = musicSdk;
        musicSdk.addListener(musicListener);
    }

    private MusicSdk.MusicListener musicListener = new MusicSdk.MusicListener() {
        @Override
        public void onPlay(Object stream) {
            System.out.println("Play stream on speaker right");
        }
    };
}
