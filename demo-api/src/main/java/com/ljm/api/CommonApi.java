package com.ljm.api;

/**
 * 公共API
 */
public interface CommonApi {

    default long customerNo(String name){
        return -1L;
    }
}
