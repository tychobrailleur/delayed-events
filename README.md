Plugin to trigger and process delayed Spring events.

This plugin uses the spring-events (from which it borrows its wiring logic) and quartz plugins.

# Installation

Add the following to your plugins in grails-app/conf/BuildConfig.groovy:


    plugins {
        compile ':delayed-events:0.1'
    }

# Usage

The delayed events plugin adds a publishDelayedEvent method to domain
classes, controllers and services in a fashion similar to what
spring-events does.  When triggering a delayed event, just call the
following method:

    publishDelayedEvent(this, theEvent, 150000)

The first parameter is the source of the event, the second the Spring
event to trigger after some time, and the last one is the delay after
which you want the event to fire.

# Caveats

The delayed events plugin uses a scheduled job that runs every second
to process the event queue; once the queue is cleared, it will only
run one second later.  This means that events coming right after the
queue has been cleared will be processed one second after if their
delay has expired.  In other words, there is no guarantee as to when
exactly a delayed event will execute.
