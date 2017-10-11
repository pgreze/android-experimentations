package dagger.step1;

public class MusicAnalytics {

    public MusicAnalytics() {}

    public void track(Object event) {
        System.out.println("Monitor event: " + event);
    }
}
