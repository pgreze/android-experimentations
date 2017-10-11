package pgreze.dagger2.app

import android.app.Application
import android.content.Context

import javax.inject.Inject

import pgreze.dagger2.analytics.AnalyticsManager
import pgreze.dagger2.di.HasComponent

class Dagger2App : Application(), HasComponent<AppComponent> {

    @Inject
    internal lateinit var analyticsManager : AnalyticsManager

    override val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        analyticsManager.event("App Opened")
    }

    companion object {

        fun getInstance(context: Context): Dagger2App {
            return context.applicationContext as Dagger2App
        }
    }
}
