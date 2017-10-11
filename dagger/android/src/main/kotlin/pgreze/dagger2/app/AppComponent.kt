package pgreze.dagger2.app

import android.content.Context

import javax.inject.Singleton

import dagger.Component
import pgreze.dagger2.analytics.AnalyticsManager

/**
 * App component.
 * With @Singleton annotation, all @Singleton objects will be global to the app.
 * @see pgreze.dagger2.di.AndroidLifecycleScope for activity/service single and lifecycle related
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: Dagger2App)

    // We have to manually defined these accessors for objects used by other components

    fun context(): Context
    fun analyticsManager(): AnalyticsManager
}
