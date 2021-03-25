### 用户表
CREATE TABLE `template-project`.sys_user(
id int PRIMARY key auto_increment COMMENT '主键',
type VARCHAR(16) not null COMMENT '用户类型',
username VARCHAR(32) not null COMMENT '用户名',
password VARCHAR(64) not null COMMENT '密码',
name VARCHAR(32) COMMENT '姓名',
phone VARCHAR(32) COMMENT '电话',
email VARCHAR(64) COMMENT '邮箱',
create_by int DEFAULT null COMMENT '创建人',
create_time TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_by int DEFAULT null COMMENT '修改人',
update_time TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
remark VARCHAR(255) DEFAULT null COMMENT '备注'
)ENGINE=INNODB;

### 用户角色
CREATE TABLE `template-project`.sys_user_role(
id int PRIMARY key auto_increment COMMENT '主键',
role_key VARCHAR(32) not null COMMENT '角色类型',
role_name VARCHAR(32) COMMENT '角色名',
role_desc VARCHAR(64) COMMENT '角色说明',
create_by int DEFAULT null COMMENT '创建人',
create_time TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
remark VARCHAR(255) DEFAULT null COMMENT '备注'
)ENGINE=INNODB;

### 用户权限
CREATE TABLE `template-project`.sys_user_permission(
id int PRIMARY key auto_increment COMMENT '主键',
perm_key VARCHAR(32) not null COMMENT '权限键',
perm_name VARCHAR(32) COMMENT '权限名',
perm_desc VARCHAR(64) COMMENT '权限说明',
create_by int DEFAULT null COMMENT '创建人',
create_time TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
remark VARCHAR(255) DEFAULT null COMMENT '备注'
)ENGINE=INNODB;

### 用户角色关联
CREATE TABLE `template-project`.sys_user_role_ref(
id int PRIMARY key auto_increment COMMENT '主键',
sys_user_id int not null COMMENT '用户ID',
role_id int not null COMMENT '角色ID',
create_by int DEFAULT null COMMENT '创建人',
create_time TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
remark VARCHAR(255) DEFAULT null COMMENT '备注'
)ENGINE=INNODB;

### 角色权限关联
CREATE TABLE `template-project`.sys_user_role_permission_ref(
id int PRIMARY key auto_increment COMMENT '主键',
role_id int not null COMMENT '角色ID',
permission_id int not null COMMENT '权限ID',
create_by int DEFAULT null COMMENT '创建人',
create_time TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
remark VARCHAR(255) DEFAULT null COMMENT '备注'
)ENGINE=INNODB;

### 资源权限配置表
CREATE TABLE `template-project`.sys_url_permission_ref(
id int PRIMARY key auto_increment COMMENT '主键',
permission_id int not null COMMENT '权限ID',
url VARCHAR(255) not null COMMENT '资源路径',
url_desc VARCHAR(128) DEFAULT null COMMENT '资源说明',
create_by int DEFAULT null COMMENT '创建人',
create_time TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
remark VARCHAR(255) DEFAULT null COMMENT '备注'
)ENGINE=INNODB;