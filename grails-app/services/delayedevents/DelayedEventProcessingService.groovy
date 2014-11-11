package delayedevents

import org.springframework.context.ApplicationListener

import java.util.concurrent.DelayQueue

class DelayedEventProcessingService implements ApplicationListener<DelayedEvent> {
    final DelayQueue<DelayedEvent> delayQueue = new DelayQueue<DelayedEvent>()
    @Override
    void onApplicationEvent(DelayedEvent event) {
        log.debug("Delayed event triggered with delay ${event.delay}")
        delayQueue.add(event)
    }

    DelayedEvent pollAndFire() {
        DelayedEvent event = delayQueue.take()
        publishEvent(event.originalEvent)
    }

    boolean isEmpty() {
        delayQueue.size() <= 0
    }
}
