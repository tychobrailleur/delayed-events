package delayedevents

import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j

import java.util.concurrent.DelayQueue

import org.springframework.context.ApplicationListener

@CompileStatic
@Slf4j
class DelayedEventProcessingService implements ApplicationListener<DelayedEvent> {

    static transactional = false

    private final DelayQueue<DelayedEvent> delayQueue = new DelayQueue<DelayedEvent>()

    void onApplicationEvent(DelayedEvent event) {
        log.debug("Delayed event triggered with delay ${event.delay}")
        delayQueue << event
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    DelayedEvent pollAndFire() {
        publishEvent(delayQueue.take().originalEvent)
    }

    boolean isEmpty() {
        delayQueue.empty
    }
}
