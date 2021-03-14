package com.ljm.model.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ljm.enums.SysUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author create by jiamingl on 下午9:15
 * @title 系统用户信息
 * @desc
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysUser implements Serializable {

    // 反序列化版本
    private static final long serialVersionUID = 1712868767756464338L;

    @TableId(type=IdType.AUTO)
    private Long id;

    private SysUserType type;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String email;

    private LocalDateTime createTime;

    private Long createBy;

    private LocalDateTime updateTime;

    private Long updateBy;

    private String remark;

}
