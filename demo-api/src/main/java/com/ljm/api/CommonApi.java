package com.ljm.api;

/**
 * 公共API
 */
public interface CommonApi {

    default long getCustomerNoByNames(String name){
        return -1L;
    }
}
