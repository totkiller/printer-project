/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.prototype.advancedprinter.event;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Ronaldo Totini
 */
public class EventNotifier {

    private static EventNotifier eventManager;
    private Set<EventNotificationListener> multicastList;
    private EventTypeEnum currentEvent;

   
   
    private Source source;

    private EventNotifier() {

    }

    public static EventNotifier getInstance() {

        if (eventManager == null) {
            eventManager = new EventNotifier();

        }

        return eventManager;
    }

    public Set<EventNotificationListener> getMulticastList() {
        if (multicastList == null) {
            multicastList = new HashSet<>();
        }
        return multicastList;
    }

    public void addEventNotificationListener(EventNotificationListener eventNotificationListener) {
        getMulticastList().add(eventNotificationListener);
    }
    
     public void removeEventNotificationListener(EventNotificationListener eventNotificationListener) {
        getMulticastList().remove(eventNotificationListener);
    }

    public void setSource(Source source) {
        this.source = source;
    }
    
     public EventTypeEnum getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(EventTypeEnum currentEvent) {
        this.currentEvent = currentEvent;
    }

    public void performNotification() {

        for (EventNotificationListener event : getMulticastList()) {
            event.eventTriggered(source,currentEvent);
        }

    }
    
    public void reset(){
        this.source=null;
        getInstance().currentEvent=EventTypeEnum.NONE;
        getInstance().performNotification();
    }

}
