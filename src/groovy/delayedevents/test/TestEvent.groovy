package delayedevents.test

import org.springframework.context.ApplicationEvent

class TestEvent extends ApplicationEvent {
    TestEvent(def source) {
        super(source)
    }
}
