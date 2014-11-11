package delayedevents

class ProcessDelayedEventsJob {
    def static jobName = this.name
    def delayedEventProcessingService
    def concurrent = false
    public final static String cronExpression = '0/1 * * * * ?'

    static triggers = {
        cron name: name, cronExpression: cronExpression
    }

    def execute() {
        log.debug("${jobName} executing...")
        while (!delayedEventProcessingService.isEmpty()) {
            delayedEventProcessingService.pollAndFire()
        }
    }
}
