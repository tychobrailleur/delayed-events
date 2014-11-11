package delayedevents


import org.springframework.context.ApplicationEvent

import java.util.concurrent.Delayed
import java.util.concurrent.TimeUnit

class DelayedEvent extends ApplicationEvent implements Delayed {
    ApplicationEvent originalEvent
    long delay
    long triggerTime
    
    DelayedEvent(def source, ApplicationEvent originalEvent, long delay) {
        super(source)
        this.originalEvent = originalEvent
        this.delay = delay
        this.triggerTime = System.currentTimeMillis() + delay
    }

    @Override
    int compareTo(Delayed obj) {
        if (!obj?.triggerTime || !triggerTime) return 0;
        return triggerTime.compareTo(obj.triggerTime)
    }

    @Override
    long getDelay(TimeUnit unit) {
        long updated = triggerTime - System.currentTimeMillis()
        return unit.convert(updated, TimeUnit.MILLISECONDS)
    }
}
