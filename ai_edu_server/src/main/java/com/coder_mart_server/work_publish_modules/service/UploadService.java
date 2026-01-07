package com.coder_mart_server.work_publish_modules.service;


import com.coder_mart_server.work_publish_modules.students.pojo.dto.UploadSelfHomeworkDTO;
import com.coder_mart_server.work_publish_modules.students.pojo.vo.AnswerVO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.dto.*;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.HomeworkVO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.ResourceVO;

import java.util.List;

public interface UploadService {

  void uploadResources(UploadResourceDTO uploadResourceDTO, Long classId);

  List<ResourceVO> research(Long classId);

  void deleteResource(LogicDeleteDTO logicDeleteDTO);

  void uploadHomework(UploadHomeworkDTO uploadHomeworkDTO, Long classId);

  List<HomeworkVO> queryHomework(Long classId);

  void uploadMyAnswer(UploadSelfHomeworkDTO uploadSelfHomeworkDTO);

  List<AnswerVO> queryAllAnswer(Long classId,Long homeworkId);
}
