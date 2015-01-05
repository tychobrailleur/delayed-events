grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        mavenLocal()
        grailsCentral()
        mavenCentral()
    }

    plugins {
        build ':release:2.2.1', ':rest-client-builder:1.0.3', {
            export = false
        }

        compile(":hibernate:$grailsVersion") {
            export = false
        }

        compile ":spring-events:1.2"
        compile ":quartz2:2.1.6.2"
    }
}
