package com.coder_mart_server.work_publish_modules.service;


import com.coder_mart_server.work_publish_modules.teachers.pojo.dto.LogicDeleteDTO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.dto.UploadHomeworkDTO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.HomeworkVO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.ResourceVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {

  void uploadResources(List<MultipartFile>multipartFileList,List<String> resourceName, Long classId);

  List<ResourceVO> research(Long classId);

  void deleteResource(LogicDeleteDTO logicDeleteDTO);

  void uploadHomework(List<MultipartFile>multipartFileList, List<UploadHomeworkDTO> uploadHomeworkDTO,Long classId);

  List<HomeworkVO> queryHomework(Long classId);
}
