package com.ljm.api;

/**
 * 公共API
 */
public interface CommonApi {

    public default long customerNo(String name){
        return -1L;
    }
}
