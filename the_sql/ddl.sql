# 创建ai星链教育平台数据库
create database if not exists star_ai_edu;

use star_ai_edu;
# 用户系统

drop table if exists t_user;
# 用户表
create table if not exists t_user(
    user_id bigint not null primary key comment '用户id',
    username varchar(10) not null comment '用户名',
    password varchar(10) not null comment '密码',
    name varchar(10) not null comment '昵称',
    age int not null comment '年龄',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    version int not null comment '版本号',
    deleted int not null comment '逻辑删除',
    unique(username,password,deleted)
    );

drop table if exists t_class;
# 班级表
create table if not exists t_class(
    class_id bigint not null primary key comment '班级id',
    teacher_id bigint not null comment '班级的管理老师id',
    class_name varchar(10) not null comment '班级名称',
    grade int not null comment '年级',
    student_num int not null comment '学生数量',
    max_student_num int not null comment '最大学生数量',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    version int not null comment '版本号',
    deleted int not null comment '逻辑删除',
    unique(class_id,teacher_id,deleted)
    );

drop table if exists t_class_roster;
# 班级花名册表
create table if not exists t_class_roster(
     class_id bigint not null comment '对应班级id',
     student_id bigint not null comment '学生id',
     create_time datetime not null comment '创建时间',
     update_time datetime not null comment '更新时间',
     version int not null comment '版本号',
     deleted int not null comment '逻辑删除',
     unique(class_id,student_id,deleted)
    );

drop table if exists t_role;
# 权限表
create table if not exists t_role(
    role_id bigint not null primary key comment '职位id',
    role_type int not null comment '职位类型（0为学生，1为老师）',
    role_name varchar(10) not null comment '职位名称',
    role_content json not null comment '权限内容',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '更新时间',
    version int not null comment '版本号',
    deleted int not null comment '逻辑删除',
    unique(role_type,role_name,deleted)
    );

drop table if exists t_role_roster;
# 权限花名册表
create table if not exists t_role_roster(
        user_id bigint not null comment '用户id（包括学生和老师）',
        role_id bigint not null comment '职位id',
        create_time datetime not null comment '创建时间',
        update_time datetime not null comment '更新时间',
        version int not null comment '版本号',
        deleted int not null comment '逻辑删除',
        unique(user_id,role_id,deleted)
    );