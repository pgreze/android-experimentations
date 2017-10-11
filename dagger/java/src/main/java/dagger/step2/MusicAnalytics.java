package dagger.step2;

import javax.inject.Inject;

public class MusicAnalytics {

    @Inject
    public MusicAnalytics() {}

    public void track(Object event) {
        System.out.println("Monitor event: " + event);
    }
}
