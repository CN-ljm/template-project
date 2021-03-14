package com.ljm.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author create by jiamingl on 下午10:17
 * @title 用户角色关联表
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUserRoleRef implements Serializable {

    private static final long serialVersionUID = 7202487209191784744L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long sysUserId;

    private Long roleId;

    private LocalDateTime createTime;

    private Long createBy;

    private String remark;

}
