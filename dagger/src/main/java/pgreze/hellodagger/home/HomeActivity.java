package pgreze.hellodagger.home;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import dagger.ObjectGraph;
import pgreze.hellodagger.R;
import pgreze.hellodagger.app.HelloDaggerApp;

public class HomeActivity extends ActionBarActivity {

    private ObjectGraph objectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create object graph
        objectGraph = ((HelloDaggerApp) getApplication()).createScopedGraph(new HomeModule(this));
        objectGraph.inject(this);

        // Inflate fragments
        setContentView(R.layout.activity_home);
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        objectGraph = null;
    }
}
