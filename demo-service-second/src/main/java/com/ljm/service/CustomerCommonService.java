package com.ljm.service;

import com.ljm.api.CommonApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommonService {

    @Reference
    private CommonApi commonApi;

    /**
     * 获取客户号
     * @param name
     * @return
     */
    public long getCustomerNoByNames(String name){
        return commonApi.getCustomerNoByNames(name);
    }

}
