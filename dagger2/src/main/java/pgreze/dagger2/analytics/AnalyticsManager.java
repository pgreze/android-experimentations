package pgreze.dagger2.analytics;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AnalyticsManager {

    private final MessageService messageService;

    /**
     * Constructor with @Inject will be used by dagger
     * @param messageService
     */
    @Inject
    public AnalyticsManager(MessageService messageService) {
        this.messageService = messageService;
    }

    public void event(String event) {
        messageService.showMessage("Register " + event);
    }
}
