package com.coder_mart_server.work_publish_modules.teachers.pojo.dto;

import lombok.Data;

import java.util.List;

@Data

public class LogicDeleteDTO {

   private List<Long> resourceIdList;

   private Long classId;


}
