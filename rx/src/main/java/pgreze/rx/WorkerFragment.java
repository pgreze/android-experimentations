package pgreze.rx;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pgreze.rx.weather.WeatherService;
import pgreze.rx.weather.WeatherServiceBuilder;
import pgreze.rx.weather.City;
import rx.Observable;
import rx.schedulers.Schedulers;

public class WorkerFragment extends Fragment {
    public static final String TAG = WorkerFragment.class.getName();

    private WeatherService service;
    private Observable<List<List<String>>> request;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fragment will be retained while activity is recreated
        // Should only be used with non UI fragment
        setRetainInstance(true);

        if (service == null) {
            Log.i(TAG, "Init worker");
            service = WeatherServiceBuilder.build();

            // City ids (something like New York, Tokyo, Paris, Kyoto)
            String ids = TextUtils.join(",", new Object[]{1850147, 2988507, 5128638, 1857910});
            request = service.weatherByCityIds(ids) // Request with retrofit
                    .map(RxActivity.log(TAG, "weather received"))
                    .flatMap(cities -> {
                        // Filter only city in Japan
                        // This work will be done in retrofit scheduler
                        List<City> ret = new ArrayList<>(cities.list.size());
                        int ignored = 0;
                        for (City city : cities.list) {
                            if (city.sys.country.equals("JP")) {
                                ret.add(city);
                            } else {
                                ignored++;
                            }
                        }

                        Log.i(TAG, String.format("[%s] %s/%s cities aren't in JP",
                                Thread.currentThread().getName(), ignored, cities.list.size()));
                        return Observable.just(ret);
                    })
                    .map(RxActivity.log(TAG, "filtered with flatMap"))
                    .observeOn(Schedulers.computation()) // Following actions will use a computation thread
                    .map(cities -> {
                        // Work done on computation thread
                        List<List<String>> ret = new ArrayList<>(cities.size());
                        for (City city : cities) {
                            String weather = city.weather.size() == 0 ? null : city.weather.get(0).description;
                            ret.add(Arrays.asList(city.name, weather));
                        }
                        return ret;
                    })
                    .map(RxActivity.log(TAG, "conversion done"))
                    .cache() // Previous work will be directly returned for future requests (Network/filter/convert)
                    .map(RxActivity.log(TAG, "from cache"));

            // With previous request, first screen logs:
            // ReposWorkerFragment: Init worker
            // ReposWorkerFragment: [Retrofit-Idle] weather received
            // ReposWorkerFragment: [Retrofit-Idle] 2/4 cities aren't in JP
            // ReposWorkerFragment: [Retrofit-Idle] filtered with flatMap
            // ReposWorkerFragment: [RxComputationThreadPool-4] conversion done
            // ReposWorkerFragment: [RxComputationThreadPool-4] from cache
            // ReposFragment: Received [[Tokyo, light rain], [Kyoto, broken clouds]]
            //
            // And with a screen rotate:
            // ReposWorkerFragment: [main] from cache
            // ReposFragment: Received [[Tokyo, light rain], [Kyoto, broken clouds]]
        }
    }

    public Observable<List<List<String>>> getRequest() {
        return request;
    }

}
