package dagger.step3.music;

import java.util.ArrayList;
import java.util.List;

public class MusicSdk {

    public interface MusicListener {
        void onPlay(Object stream);
    }

    MusicPlayer musicPlayer;
    MusicAnalytics musicAnalytics;

    List<MusicListener> listeners = new ArrayList<>();

    public MusicSdk(MusicPlayer musicPlayer, MusicAnalytics musicAnalytics) {
        this.musicPlayer = musicPlayer;
        this.musicAnalytics = musicAnalytics;
    }

    public void addListener(MusicListener listener) {
        listeners.add(listener);
    }

    public void play(Object track) {
        musicPlayer.play(track);
        musicAnalytics.track(track);

        for (MusicListener listener: listeners) {
            // TODO: convert track to stream
            listener.onPlay(track.hashCode());
        }
    }
}
