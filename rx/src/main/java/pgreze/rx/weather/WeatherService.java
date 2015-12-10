package pgreze.rx.weather;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface WeatherService {

    String BASE = "http://api.openweathermap.org/data/2.5";

    @GET("/group?units=metric")
    Observable<GroupList<City>> weatherByCityIds(@Query("id") String ids);
}
