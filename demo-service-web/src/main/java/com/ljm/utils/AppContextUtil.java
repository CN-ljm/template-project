package com.ljm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*@Component
public class AppContextUtil implements ApplicationContextAware {

    private static ApplicationContext appContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    public static ApplicationContext getAppContext(){
        return appContext;
    }

    public static <T> T getBean(Class<T> tClass){
        return getAppContext().getBean(tClass);
    }

    public static Object getBean(String beanName){
        return getAppContext().getBean(beanName);
    }
}*/
