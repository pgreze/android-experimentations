package pgreze.dagger2.ui.hello;

import javax.inject.Inject;

import pgreze.dagger2.analytics.AnalyticsManager;
import pgreze.dagger2.di.UILifecycleScope;

@UILifecycleScope
public class HelloPresenter {

    private final AnalyticsManager analyticsManager;
    private HelloFragment fragment;

    private int nClick = 0;

    @Inject
    public HelloPresenter(AnalyticsManager analyticsManager) {
        this.analyticsManager = analyticsManager;
    }

    public void init(HelloFragment fragment) {
        this.fragment = fragment;
    }

    public void onHelloClick() {
        nClick++;
        fragment.getHelloBtn().setText(String.format("Retry (%s time(s))", nClick));
        analyticsManager.event("Hello Button Clicked");
    }
}
