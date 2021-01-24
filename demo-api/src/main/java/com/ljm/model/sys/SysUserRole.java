package com.ljm.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author create by jiamingl on 下午10:11
 * @title 角色管理
 * @desc
 */
@Data
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 689793282767283436L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String desc;

    private LocalDateTime createTime;

    private Long createBy;

    private String remark;


}
