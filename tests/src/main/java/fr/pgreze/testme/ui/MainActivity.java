package fr.pgreze.testme.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.pgreze.testme.domain.common.di.HasComponent;
import fr.pgreze.testme.ui.common.di.ActivityComponent;
import fr.pgreze.testme.ui.common.di.DaggerActivityComponent;
import fr.pgreze.testme.ui.gist.PublicGistsFragment;

public class MainActivity extends AppCompatActivity implements HasComponent<ActivityComponent> {

    private ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new PublicGistsFragment(), PublicGistsFragment.TAG)
                    .commit();
        }
    }

    @Override
    public ActivityComponent component() {
        if (component == null) {
            component = DaggerActivityComponent.builder()
                    .appComponent(TestApp.getInstance(this).component())
                    .build();
        }
        return component;
    }
}
