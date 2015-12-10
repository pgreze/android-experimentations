package pgreze.dagger2.home;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import pgreze.dagger2.R;
import pgreze.dagger2.app.AppComponent;
import pgreze.dagger2.app.Dagger2App;
import pgreze.dagger2.di.HasComponent;

public class HomeActivity extends ActionBarActivity implements HasComponent<AppComponent> {
    private static final String TAG = HomeActivity.class.getName();

    private HomeComponent component;

    @Inject @Named("ActivityScope")
    StringBuilder activityScope1;

    @Inject @Named("ActivityScope")
    StringBuilder activityScope2;

    @Inject @Named("Unscoped")
    StringBuilder unscoped1;

    @Inject @Named("Unscoped")
    StringBuilder unscoped2;

    @Inject
    StringBuilder builder1;

    @Inject
    StringBuilder builder2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Create home component
        component = DaggerHomeComponent.builder()
                .build();
        // And resolve HomeActivity dependencies
        component.inject(this);

        // For @Named("ActivityScope") injections, we can see that's the same object (because of @ActivityScope)
        Log.i(TAG, "activityScope1: " + activityScope1); // activityScope1: activity 397029449
        Log.i(TAG, "activityScope2: " + activityScope2); // activityScope2: activity 397029449

        // "Unscoped" StringBuilders aren't in a scope. So, we have one object per dependency
        Log.i(TAG, "unscoped1: " + unscoped1); // unscoped1: Unscoped -1924673827
        Log.i(TAG, "unscoped2: " + unscoped2); // unscoped2: Unscoped -883383397

        // No name, no scope, standard injection
        Log.i(TAG, "builder1: " + builder1); // builder1: 1407504574
        Log.i(TAG, "builder2: " + builder2); // builder2: -1291519694
    }

    @Override
    public AppComponent component() {
        return Dagger2App.getInstance(this).component();
    }
}
