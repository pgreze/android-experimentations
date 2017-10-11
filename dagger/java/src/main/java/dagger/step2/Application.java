package dagger.step2;

import javax.inject.Inject;

public class Application {

    private final MusicSdk musicSdk;

    @Inject
    public Application(MusicSdk musicSdk) {
        this.musicSdk = musicSdk;
    }

    public void run() {
        musicSdk.play("X Japan - Kurenai");
    }

    public static void main(String[] args) {
        Application app = DaggerPlayerComponent.create().instance();
        app.run();
    }
}
