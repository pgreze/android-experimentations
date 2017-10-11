package pgreze.dagger2.app

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import pgreze.dagger2.BuildConfig

@Module
class AppModule(private val app: Dagger2App) {

    /**
     * Mandatory @Provides method because we're injecting:
     *
     * - An external object. Context is not defined in our project
     * - An object without available constructor. It's common with Android objects: App/Activity/Service
     *
     * But no need to use @Singleton, we're always returning the same field.
     * @return app context
     */
    @Provides fun provideApplication(): Context {
        return app
    }

    /**
     * Example for an explicit @Provides method.
     * We're using @Singleton so eac instance will be the same
     * @param context provided by the previous method. Use this.app is not forbidden :)
     * *
     * @return single shared preferences for the app
     */
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }
}
