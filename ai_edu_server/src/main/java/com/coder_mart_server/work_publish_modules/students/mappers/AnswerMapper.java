package com.coder_mart_server.work_publish_modules.students.mappers;


import com.coder_mart_server.work_publish_modules.students.pojo.vo.AnswerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper {

    List<AnswerVO> queryAllAnswer();

}
