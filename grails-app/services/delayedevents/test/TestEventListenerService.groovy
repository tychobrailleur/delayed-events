package delayedevents.test

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import org.springframework.context.ApplicationListener

@CompileStatic
@Slf4j
class TestEventListenerService implements ApplicationListener<TestEvent> {

    static transactional = false

    void onApplicationEvent(TestEvent e) {
        log.info('Received event!')
    }
}
