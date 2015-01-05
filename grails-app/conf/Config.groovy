log4j = {

    root {
        info  'stdout'
    }

    error 'org.codehaus.groovy.grails',
          'org.springframework',
          'org.hibernate',
          'net.sf.ehcache.hibernate'

    debug 'delayedevents'
}
