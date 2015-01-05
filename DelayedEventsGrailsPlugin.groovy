import grails.plugin.springevents.AsyncEventPublisher
import grails.plugin.springevents.GrailsApplicationEventMulticaster
import delayedevents.DelayedEvent
import delayedevents.DelayedEventProcessingService

class DelayedEventsGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.2 > *"
    def pluginExcludes = [
        "grails-app/domain/**",
        "grails-app/views/**",
        "src/templates/**",
        "**/test/**",
        "web-app/**"
    ]
    def title = "Delayed Events Plugin"
    def author = "Sébastien Le Callonnec"
    def authorEmail = "sebastien@weblogism.com"
    def description = "Support for delayed events"
    def documentation = "http://grails.org/plugin/delayed-events"
    def issueManagement = [ system: 'github', url: 'https://github.com/tychobrailleur/delayed-events/issues' ]
    def scm = [ url: 'https://github.com/tychobrailleur/delayed-events' ]
    def license = 'MIT'

    def doWithSpring = {
        asyncApplicationEventMulticaster(GrailsApplicationEventMulticaster) {
            persistenceInterceptor = ref('persistenceInterceptor')
        }
        asyncEventPublisher(AsyncEventPublisher) {
            eventMulticaster = ref('asyncApplicationEventMulticaster')
        }
        delayedEventProcessingService(DelayedEventProcessingService)
    }

    def doWithDynamicMethods = { ctx ->
        [application.controllerClasses, application.serviceClasses, application.domainClasses].flatten().each {
            addPublishDelayedEvent(it, ctx)
        }
    }

    private void addPublishDelayedEvent(subject, ctx) {
        subject.metaClass.publishDelayedEvent = { DelayedEvent event ->
            ctx.asyncEventPublisher.publishEvent(event)
        }
    }
}
