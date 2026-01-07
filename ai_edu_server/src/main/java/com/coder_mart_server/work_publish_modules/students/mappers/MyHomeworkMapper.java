package com.coder_mart_server.work_publish_modules.students.mappers;

import com.coder_mart_server.work_publish_modules.students.pojo.entity.ContentEntity;
import com.coder_mart_server.work_publish_modules.students.pojo.entity.StuAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyHomeworkMapper {

    //上传图片或文件形式答案
    void uploadMyHomework(@Param("aList") List<StuAnswer> stuAnswerList);

    //以文字内容上传答案
    void uploadContent(@Param("content") ContentEntity contentEntity);
}
