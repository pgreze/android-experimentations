package dagger.step3;

import dagger.Module;
import dagger.Provides;
import dagger.step3.hardware.SpeakerLeft;
import dagger.step3.hardware.SpeakerRight;
import dagger.step3.hardware.SpeakersRemote;
import dagger.step3.music.MusicSdk;

@Module
public class HardwareModule {

    @Provides
    SpeakerLeft speakerLeft(MusicSdk musicSdk) {
        SpeakerLeft speakerLeft = SpeakerLeft.getInstance();
        speakerLeft.init(musicSdk);
        return speakerLeft;
    }

    @Provides
    SpeakerRight speakerRight(MusicSdk musicSdk) {
        SpeakerRight speakerRight = SpeakerRight.getInstance();
        speakerRight.init(musicSdk);
        return speakerRight;
    }

    @Provides
    SpeakersRemote speakersRemote(MusicSdk musicSdk) {
        SpeakersRemote speakersRemote = SpeakersRemote.getInstance();
        speakersRemote.init(musicSdk);
        return speakersRemote;
    }
}
