package pgreze.dagger2.analytics

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Constructor with @Inject will be used by dagger.
 * @param messageService a singleton scoped only dependency
 */
@Singleton
class AnalyticsManager @Inject constructor(
        private val messageService: MessageService
) {
    fun event(event: String) {
        messageService.showMessage("Register " + event)
    }
}
