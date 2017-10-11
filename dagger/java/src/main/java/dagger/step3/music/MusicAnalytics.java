package dagger.step3.music;

public class MusicAnalytics {

    public MusicAnalytics() {}

    public void track(Object event) {
        System.out.println("Monitor event: " + event);
    }
}
