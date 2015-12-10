package pgreze.dagger2.ui.player;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import pgreze.dagger2.di.UILifecycleScope;

@UILifecycleScope
public class PlayerInteractor {

    public enum Status { PLAY, PAUSE, STOP }

    private final Context context;
    private Status current = Status.STOP;

    @Inject
    public PlayerInteractor(Context context) {
        this.context = context;
    }

    public Status onIndicatorClick() {
        // Resolve new status
        Status newStatus = current == Status.PLAY ? Status.PAUSE : Status.PLAY;

        // Process status
        Toast.makeText(context, "Process " + newStatus, Toast.LENGTH_SHORT).show();
        this.current = newStatus;

        // Return new status
        return current;
    }

    public Status getStatus() {
        return current;
    }
}
