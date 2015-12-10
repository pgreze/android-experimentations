package pgreze.rx.weather;

import retrofit.RestAdapter;

public class WeatherServiceBuilder {

    public static WeatherService build() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(WeatherService.BASE)
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setRequestInterceptor(request -> {
                    request.addHeader("Accept", "application/json");
                })
                .build();
        return restAdapter.create(WeatherService.class);
    }
}
