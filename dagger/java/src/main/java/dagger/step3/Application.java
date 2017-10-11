package dagger.step3;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.step3.hardware.SpeakerLeft;
import dagger.step3.hardware.SpeakerRight;
import dagger.step3.hardware.SpeakersRemote;

public class Application {

    private final List<Object> speakers;
    private final SpeakersRemote speakersRemote;

    @Inject
    public Application(SpeakerLeft speakerLeft, SpeakerRight speakerRight, SpeakersRemote speakersRemote) {
        speakers = Arrays.asList(speakerLeft, speakerRight);
        this.speakersRemote = speakersRemote;
    }

    public void run() {
        System.out.println(String.format("Playing music with %s speakers", speakers.size()));
        speakersRemote.play("X Japan - Kurenai");
    }

    public static void main(String[] args) {
        Application app = DaggerAppComponent.create().instance();
        app.run();
    }
}
