package delayedevents

import groovy.transform.CompileStatic

class ProcessDelayedEventsJob {

    static String jobName = this.name

    DelayedEventProcessingService delayedEventProcessingService

    boolean concurrent = false

    public static final String cronExpression = '0/1 * * * * ?'

    static triggers = {
        cron name: name, cronExpression: cronExpression
    }

    @CompileStatic
    void execute() {
        while (!delayedEventProcessingService.empty) {
            delayedEventProcessingService.pollAndFire()
        }
    }
}
