package dagger.step1;

public class Application {

    private final MusicSdk musicSdk;

    public Application(MusicSdk musicSdk) {
        this.musicSdk = musicSdk;
    }

    public void run() {
        musicSdk.play("X Japan - Kurenai");
    }

    public static void main(String[] args) {
        MusicSdk musicSdk = new MusicSdk(
                new MusicPlayer(),
                new MusicAnalytics());
        Application app = new Application(musicSdk);
        app.run();
    }
}
