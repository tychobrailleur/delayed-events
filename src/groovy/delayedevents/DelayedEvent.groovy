package delayedevents

import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

import java.util.concurrent.Delayed
import java.util.concurrent.TimeUnit

import org.springframework.context.ApplicationEvent

@CompileStatic
class DelayedEvent extends ApplicationEvent implements Delayed {
    final ApplicationEvent originalEvent
    final long delay
    final long triggerTime

    DelayedEvent(source, ApplicationEvent originalEvent, long delay) {
        super(source)
        this.originalEvent = originalEvent
        this.delay = delay
        triggerTime = System.currentTimeMillis() + delay
    }

    @CompileStatic(TypeCheckingMode.SKIP)
    int compareTo(Delayed obj) {
        if (!obj?.triggerTime || !triggerTime) return 0
        return triggerTime.compareTo(obj.triggerTime)
    }

    long getDelay(TimeUnit unit) {
        long updated = triggerTime - System.currentTimeMillis()
        return unit.convert(updated, TimeUnit.MILLISECONDS)
    }
}
