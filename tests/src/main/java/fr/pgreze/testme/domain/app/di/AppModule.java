package fr.pgreze.testme.domain.app.di;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides Context provideContext() {
        return app;
    }

    @Singleton @Provides OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Singleton @Provides Picasso providePicasso(OkHttpClient httpClient) {
        return new Picasso.Builder(app)
                .downloader(new OkHttpDownloader(httpClient))
                .build();
    }

    @Singleton @Provides Gson provideGson() {
        return new Gson();
    }
}
