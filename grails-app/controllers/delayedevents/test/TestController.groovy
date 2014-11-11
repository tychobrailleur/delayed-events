package delayedevents.test

import delayedevents.*


class TestController {
    def index() {
        int delay = params.delay ? params.int('delay') : 15000
        
        def anEvent = new TestEvent(this)
        def delayedEvent = new DelayedEvent(this, anEvent, delay)
        log.info("Triggering delayed event with delay = $delay")
        publishDelayedEvent(delayedEvent)
    }
}
