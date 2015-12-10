package pgreze.hellodagger.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.Arrays;
import java.util.List;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inject fragment related dependencies via activity
        // We can't do it in onActivityCreated, invoked after onViewCreated
        ((HomeActivity) getActivity()).getObjectGraph().plus(getModules().toArray()).inject(this);
    }

    public List<? extends Object> getModules() {
        return Arrays.asList();
    }
}
