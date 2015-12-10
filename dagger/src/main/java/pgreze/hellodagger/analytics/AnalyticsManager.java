package pgreze.hellodagger.analytics;

import android.content.Context;
import android.widget.Toast;

public class AnalyticsManager {

    private final Context context;

    public AnalyticsManager(Context context) {
        this.context = context;
    }

    public void event(String event) {
        Toast.makeText(context, "Register " + event, Toast.LENGTH_SHORT).show();
    }
}
