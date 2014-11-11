import grails.plugin.springevents.*
import org.springframework.context.*
import delayedevents.*

class DelayedEventsGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.2 > *"
	def pluginExcludes = [
			"grails-app/domain/**/*",
			"src/templates/**/*",
			"**/delayedevents/test/**/*",
			"web-app/**/*"
	]

    def title = "Delayed Events Plugin" 
    def author = "Sébastien Le Callonnec"
    def authorEmail = "sebastien@weblogism.com"
    def description = "Support for delayed events"

    def documentation = "http://grails.org/plugin/delayed-events"

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
            addPublishDelayedEvent(it, application.mainContext)
        }
    }

    def addPublishDelayedEvent(subject, ctx) {
        ApplicationEventPublisher asyncEventPublisher = ctx.asyncEventPublisher
		subject.metaClass.publishDelayedEvent = { DelayedEvent event ->
			asyncEventPublisher.publishEvent(event)
		}        
    }
}
