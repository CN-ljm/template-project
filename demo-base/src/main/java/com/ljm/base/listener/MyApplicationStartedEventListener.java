package com.ljm.base.listener;

import com.esotericsoftware.minlog.Log;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author Created by liangjiaming on 2020/7/1
 * @title
 * @Desc
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        Log.debug("MyApplicationStartedEventListener");
//        System.out.println("");
    }
}
