package delayedevents.test

import delayedevents.DelayedEvent

class TestController {

    def index(Integer delay) {
        delay = delay ?: 15000

        def anEvent = new TestEvent(this)
        def delayedEvent = new DelayedEvent(this, anEvent, delay)
        log.info("Triggering delayed event with delay = $delay")
        publishDelayedEvent(delayedEvent)
    }
}
