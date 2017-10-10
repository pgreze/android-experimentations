package pgreze.dagger2.analytics

import android.content.Context
import android.widget.Toast

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Constructor with @Inject will be used by dagger.
 *
 * This dependency is only used by Singleton only objects,
 * no need to declare it in [pgreze.dagger2.app.AppComponent].
 *
 * @param context the default application context,
 *      accessible everywhere and leading to no memory-leaks.
 */
@Singleton
class MessageService @Inject constructor(
        private val context: Context
) {
    fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}
