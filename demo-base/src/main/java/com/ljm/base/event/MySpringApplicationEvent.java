package com.ljm.base.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Created by liangjiaming on 2020/7/1
 * @title
 * @Desc
 */
public class MySpringApplicationEvent extends ApplicationEvent {

    private String eventName;

    public MySpringApplicationEvent(Object source, String eventName) {
        super(source);
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
