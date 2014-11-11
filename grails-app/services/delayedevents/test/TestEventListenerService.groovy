package delayedevents.test

import org.springframework.context.ApplicationListener

class TestEventListenerService implements ApplicationListener<TestEvent> {
    @Override
    void onApplicationEvent(TestEvent e) {
        log.info('Received event!')
    }
}
