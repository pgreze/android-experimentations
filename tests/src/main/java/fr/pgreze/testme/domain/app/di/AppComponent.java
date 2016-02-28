package fr.pgreze.testme.domain.app.di;

import javax.inject.Singleton;

import dagger.Component;
import fr.pgreze.testme.domain.github.GithubModule;

@Singleton
@Component(modules = {
        AppModule.class,
        GithubModule.class,
})
public interface AppComponent {
}
