package fr.pgreze.testme.domain.github;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fr.pgreze.testme.data.github.GithubService;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module
public class GithubModule {

    @Provides @Singleton GithubService provideGithubService(OkHttpClient httpClient, Gson gson) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(httpClient))
                .setEndpoint(GithubService.BASE_URL)
                .setRequestInterceptor(request -> {
                    // Request API v3. See https://developer.github.com/v3/
                    request.addHeader("Accept", "application/vnd.github.v3+json");
                    // See https://developer.github.com/v3/#user-agent-required
                    request.addHeader("User-Agent", "pgreze");
                })
                .build();
        return restAdapter.create(GithubService.class);
    }
}
