package pgreze.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class RxActivity extends AppCompatActivity implements UIFragment.UICommunicator {
    private static final String TAG = RxActivity.class.getName();

    private WorkerFragment workerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Log.i(TAG, "OnCreate " + savedInstanceState);

        if (savedInstanceState == null) {
            // Init ui + worker fragments
            workerFragment = new WorkerFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new UIFragment(), UIFragment.TAG)
                    .add(workerFragment, WorkerFragment.TAG)
                    .commit();
        } else {
            // Fragments already created
            workerFragment = (WorkerFragment) getSupportFragmentManager()
                    .findFragmentByTag(WorkerFragment.TAG);
        }
    }

    @Override
    public Observable<List<List<String>>> request() {
        return workerFragment.getRequest();
    }

    /** Display a msg when func is invoked during streams resolution */
    public static <T> Func1<T, T> log(String tag, String msg) {
        return o -> {
            Log.i(tag, String.format("[%s] %s", Thread.currentThread().getName(), msg));
            return o;
        };
    }
}
