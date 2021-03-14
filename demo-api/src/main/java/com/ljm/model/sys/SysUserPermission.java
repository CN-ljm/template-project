package com.ljm.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author create by jiamingl on 下午10:29
 * @title
 * @desc
 */
@Data
public class SysUserPermission implements Serializable {

    private static final long serialVersionUID = 5737679278619276871L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String key;

    private String name;

    private String desc;

    private LocalDateTime createTime;

    private Long createBy;

    private String remark;

}
