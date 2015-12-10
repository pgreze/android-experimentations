package pgreze.hellodagger.home.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import pgreze.hellodagger.R;
import pgreze.hellodagger.home.BaseFragment;

import static pgreze.hellodagger.home.player.PlayerInteractor.Status;

public class PlayerFragment extends BaseFragment {

    @Inject PlayerInteractor playerInteractor;
    private Button statusBtn;

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
        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status status = playerInteractor.onIndicatorClick();
                updateStatusBtn(status);
            }
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
