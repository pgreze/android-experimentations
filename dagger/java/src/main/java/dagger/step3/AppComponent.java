package dagger.step3;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        HardwareModule.class,
        MusicModule.class
})
public interface AppComponent {
    Application instance();
}
