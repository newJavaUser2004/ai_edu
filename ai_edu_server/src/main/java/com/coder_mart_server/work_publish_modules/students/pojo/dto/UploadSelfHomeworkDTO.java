package com.coder_mart_server.work_publish_modules.students.pojo.dto;


import lombok.Data;

import java.util.List;

@Data
public class UploadSelfHomeworkDTO {

    private List<SelfHomeworkDTO> selfHomeworkDTOList;

}
