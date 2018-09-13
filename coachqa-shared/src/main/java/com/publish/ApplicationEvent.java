package com.publish;


import java.util.Date;

@Deprecated
public final class ApplicationEvent<E> {

    // TODO: this needs to be replaced with integer. There is no use case to keep it generic

    E eventSource;
    private EventTypeD eventType;
    private Integer id;
    private Date eventDate = new Date(System.currentTimeMillis());
    private Date expirationDate = new Date(System.currentTimeMillis());

    @Override
    public String toString() {
        return "< Event - "+eventSource +" - "+ eventType+", "+ eventDate +">";
    }

//    public ApplicationEvent(){}

    public ApplicationEvent(int eventId, EventTypeD eventType, E keyedResource, Date eventDate, Date expirationDate) {
        this(eventType, keyedResource, eventDate, expirationDate);
        this.id = eventId;

    }

    public ApplicationEvent(EventTypeD eventType, E keyedResource, Date eventDate, Date expirationDate) {
        this.eventSource = keyedResource;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.expirationDate = expirationDate;
    }

    public ApplicationEvent(EventTypeD eventType, E simpleKeyedResource) {

        this.eventSource = simpleKeyedResource;
        this.eventType = eventType;
    }


    public EventTypeD getEventType() {
        return eventType;
    }

    public E getEventSource() {
        return eventSource;
    }

    public Integer getId() {
        return id;
    }


    public Date getEventDate() {
        return eventDate;
    }


    public Date getExpirationDate() {
        return expirationDate;
    }


    public void setId(int id) {
        this.id = id;
    }



}
