package com.coder_mart_server.user.user_modules.user_teacher.pojo.dto;

import io.reactivex.rxjava3.annotations.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ClassInfoDTO {
    //班级图片文件
    private MultipartFile file;

    //班级名称
    private String className;

    //年级
    private Integer grade;

    //最大学生数量
    private Integer maxStudentNum;
}
