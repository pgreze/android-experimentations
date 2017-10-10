package pgreze.dagger2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;

import pgreze.dagger2.R;
import pgreze.dagger2.app.Dagger2App;
import pgreze.dagger2.di.HasComponent;
import pgreze.dagger2.di.UILifecycleScope;

public class MainActivity extends AppCompatActivity implements HasComponent<ActivityComponent> {
    private static final String TAG = MainActivity.class.getName();

    @Inject @Named("ActivityScope") StringBuilder activityScope1;
    @Inject @Named("ActivityScope") StringBuilder activityScope2;
    @Inject @Named("Unscoped")      StringBuilder unscoped1;
    @Inject @Named("Unscoped")      StringBuilder unscoped2;
    @Inject StringBuilder builder1;
    @Inject StringBuilder builder2;

    /**
     * This component is {@link UILifecycleScope} annotated,
     * so it will be tight to the activity lifecycle.<br/>
     * In other words, when activity will be destroyed,
     * all {@link UILifecycleScope} objects will be lost (no memory leak)
     */
    private ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Resolve HomeActivity dependencies
        component().inject(this);

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
    public ActivityComponent component() {
        if (component == null) {
            component = DaggerActivityComponent.builder()
                    .appComponent(Dagger2App.getInstance(this).component())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return component;
    }
}
