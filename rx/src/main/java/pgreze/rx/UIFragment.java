package pgreze.rx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class UIFragment extends Fragment {
    public static final String TAG = UIFragment.class.getName();

    public interface UICommunicator {
        Observable<List<List<String>>> request();
    }

    private ListView list;
    private Subscription subscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ui, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = ((ListView) view);
    }

    @Override
    public void onStart() {
        super.onStart();
        subscription = ((UICommunicator) getActivity()).request()
                .observeOn(AndroidSchedulers.mainThread()) // UI actions will use UI thread
                .subscribe(
                        lists -> { // onNext
                            Log.i(TAG, "Received " + lists);
                            list.setAdapter(new ArrayAdapter<>(
                                    getActivity(),
                                    android.R.layout.simple_list_item_1,
                                    lists));
                        },
                        e -> Log.e(TAG, "Error with request req: " + e), // onError
                        () -> {}); // onComplete
    }

    @Override
    public void onStop() {
        subscription.unsubscribe();
        super.onStop();
    }
}
