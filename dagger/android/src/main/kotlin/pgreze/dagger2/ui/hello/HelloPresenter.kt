package pgreze.dagger2.ui.hello

import javax.inject.Inject

import pgreze.dagger2.analytics.AnalyticsManager
import pgreze.dagger2.di.AndroidLifecycleScope

@AndroidLifecycleScope
class HelloPresenter @Inject constructor(
        private val analyticsManager: AnalyticsManager
) {
    private var fragment: HelloFragment? = null

    private var nClick = 0

    fun init(fragment: HelloFragment) {
        this.fragment = fragment
    }

    fun onHelloClick() {
        nClick++
        fragment?.helloText = String.format("Retry (%s time(s))", nClick)
        analyticsManager.event("Hello Button Clicked")
    }
}
