package com.ljm.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author create by jiamingl on 下午10:39
 * @title URL权限配置
 * @desc
 */
public class SysUrlPermissionRef implements Serializable {

    private static final long serialVersionUID = 3792908872128468935L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String url;

    private Long permissionId;

    private String urlDesc;

    private LocalDateTime createTime;

    private Long createBy;

    private String remark;

}
