package com.coder_mart_server.user.user_constant;

public class EntityDefaultConstant {

    //基类entity常数
    public class BaseEntityConstant{
        //默认版本号
        public final static Integer DEFAULT_VERSION = 0;

        //默认未删除
        public final static Integer DEFAULT_DELETED = 0;
    }

    //班级entity常数
    public class ClassEntityConstant{
        // 班级默认学生数量
        public final static Integer DEFAULT_STUDENT_NUM = 0;

        //班级默认最大学生数量
        public final static Integer DEFAULT_MAX_STUDENT_NUM = 40;
    }
}
