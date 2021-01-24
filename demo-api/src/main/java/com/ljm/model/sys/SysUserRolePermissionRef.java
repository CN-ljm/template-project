package com.ljm.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author create by jiamingl on 下午10:34
 * @title 角色权限关联表
 * @desc
 */
@Data
public class SysUserRolePermissionRef implements Serializable {

    private static final long serialVersionUID = 461741063688671113L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long roleId;

    private Long permissionId;

    private LocalDateTime createTime;

    private Long createBy;

    private String remark;

}
