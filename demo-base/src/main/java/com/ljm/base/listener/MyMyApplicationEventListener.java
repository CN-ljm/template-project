package com.ljm.base.listener;

import com.ljm.base.event.MySpringApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author Created by liangjiaming on 2020/7/1
 * @title
 * @Desc
 */
public class MyMyApplicationEventListener implements ApplicationListener<MySpringApplicationEvent> {
    @Override
    public void onApplicationEvent(MySpringApplicationEvent event) {
        System.out.println(event.getEventName());
    }
}
