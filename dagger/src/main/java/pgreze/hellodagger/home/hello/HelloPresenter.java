package pgreze.hellodagger.home.hello;

import pgreze.hellodagger.analytics.AnalyticsManager;

public class HelloPresenter {

    private final HelloFragment fragment;
    private final AnalyticsManager analyticsManager;

    private int nClick = 0;

    public HelloPresenter(HelloFragment fragment, AnalyticsManager analyticsManager) {
        this.fragment = fragment;
        this.analyticsManager = analyticsManager;
    }

    public void onHelloClick() {
        nClick++;
        fragment.getHelloBtn().setText(String.format("Retry (%s time(s))", nClick));
        analyticsManager.event("hello_click");
    }
}
