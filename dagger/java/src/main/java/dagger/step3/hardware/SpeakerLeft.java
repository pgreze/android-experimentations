package dagger.step3.hardware;

import dagger.step3.music.MusicSdk;

public class SpeakerLeft {

    private static final SpeakerLeft instance = new SpeakerLeft();

    public static SpeakerLeft getInstance() {
        return instance;
    }

    private MusicSdk musicSdk;

    private SpeakerLeft() {}

    public void init(MusicSdk musicSdk) {
        this.musicSdk = musicSdk;
        musicSdk.addListener(musicListener);
    }

    private MusicSdk.MusicListener musicListener = new MusicSdk.MusicListener() {
        @Override
        public void onPlay(Object stream) {
            System.out.println("Play stream on speaker left");
        }
    };
}
