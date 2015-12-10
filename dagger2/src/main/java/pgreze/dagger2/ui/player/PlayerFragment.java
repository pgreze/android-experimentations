package pgreze.dagger2.ui.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import pgreze.dagger2.R;
import pgreze.dagger2.di.HasComponent;
import pgreze.dagger2.ui.ActivityComponent;

import static pgreze.dagger2.ui.player.PlayerInteractor.Status;

public class PlayerFragment extends Fragment {

    @Inject PlayerInteractor playerInteractor;
    private Button statusBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HasComponent<ActivityComponent>) getActivity()).component().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_player, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        statusBtn = (Button) view.findViewById(R.id.status_btn);
        updateStatusBtn(playerInteractor.getStatus());
        statusBtn.setOnClickListener(v -> {
            Status status = playerInteractor.onIndicatorClick();
            updateStatusBtn(status);
        });
    }

    public void updateStatusBtn(Status status) {
        switch (status) {
            case PLAY:
                statusBtn.setText("Pause");
                break;
            case PAUSE:
            case STOP:
                statusBtn.setText("Play");
                break;
        }
    }
}
