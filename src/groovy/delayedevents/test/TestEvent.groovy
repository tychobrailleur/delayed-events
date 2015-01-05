package delayedevents.test

import groovy.transform.CompileStatic

import org.springframework.context.ApplicationEvent

@CompileStatic
class TestEvent extends ApplicationEvent {
    TestEvent(source) {
        super(source)
    }
}
