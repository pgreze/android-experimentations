package pgreze.dagger2.analytics;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MessageService {

    private Context context;

    @Inject
    public MessageService(Context context) {
        this.context = context;
    }

    public void showMessage(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
