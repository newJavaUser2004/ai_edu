package com.coder_mart_server.work_publish_modules.service.impl;


import com.coder_mart_server.public_modules.helppers.UniqueIdHelper;
import com.coder_mart_server.public_modules.properties.SnowProperties;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurity;
import com.coder_mart_server.work_publish_modules.service.UploadService;
import com.coder_mart_server.work_publish_modules.students.mappers.AnswerMapper;
import com.coder_mart_server.work_publish_modules.students.mappers.MyHomeworkMapper;
import com.coder_mart_server.work_publish_modules.students.pojo.dto.SelfHomeworkDTO;
import com.coder_mart_server.work_publish_modules.students.pojo.dto.UploadSelfHomeworkDTO;
import com.coder_mart_server.work_publish_modules.students.pojo.entity.ContentEntity;
import com.coder_mart_server.work_publish_modules.students.pojo.entity.StuAnswer;
import com.coder_mart_server.work_publish_modules.students.pojo.vo.AnswerVO;
import com.coder_mart_server.work_publish_modules.teachers.mappers.ClassTeacherMapper;
import com.coder_mart_server.work_publish_modules.teachers.mappers.HomeworkMapper;
import com.coder_mart_server.work_publish_modules.teachers.mappers.ResourceMapper;

import com.coder_mart_server.work_publish_modules.teachers.pojo.dto.*;
import com.coder_mart_server.work_publish_modules.teachers.pojo.entity.Homework;
import com.coder_mart_server.work_publish_modules.teachers.pojo.entity.ResourceEntity;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.HomeworkVO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.ResourceVO;
import com.coder_mart_server.work_publish_modules.util.UploadUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 老师上传资源的实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UploadServiceImpl implements UploadService {

    private  final ResourceMapper resourceMapper;
    private final ClassTeacherMapper classTeacherMapper;
    private final HomeworkMapper homeworkMapper;
    private final MyHomeworkMapper myHomeworkMapper;
    private final AnswerMapper answerMapper;

    @Resource
    private SnowProperties snowProperties;
    @Resource
    private UniqueIdHelper uniqueIdHelper;
    /**
     * 老师上传多个文件
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadResources(UploadResourceDTO uploadResourceDTO, Long classId) {


        List<ResourceEntity> resourceEntityList = new ArrayList<>();

        for (int i = 0;i<uploadResourceDTO.getResourceDTOList().size();i++){

            ResourceEntity resourceEntity = new ResourceEntity();
            Long resourceId = uniqueIdHelper.snowIdBuild();
            resourceEntity.setResourceId(resourceId);
            resourceEntity.setResourceName(uploadResourceDTO.getResourceDTOList().get(i).getResourceName());
            resourceEntity.setUrl(getFileURL(uploadResourceDTO.getResourceDTOList().get(i).getMultipartFile()));
            resourceEntity.setClassId(classId);

            resourceEntityList.add(resourceEntity);
        }
        //添加url进资源表
        resourceMapper.uploadResource(resourceEntityList);

    }

    /**
     * 查看作业
     * @param classId
     * @return
     */
    @Override
    public List<ResourceVO> research(Long classId){

       List<ResourceVO> resourceVO = resourceMapper.research(classId);

        return resourceVO;
    }

    /**
     * 逻辑删除学生
     */
    @Override
    public void deleteResource(LogicDeleteDTO logicDeleteDTO) {

        //todo 验证这个老师是不是管理这个班级的
       Long id = ISecurity.getSecureUser().getUserId();
       boolean teach = classTeacherMapper.permission(id, logicDeleteDTO.getClassId());
       if (!teach){
           //todo 返回无权限异常
       }

        resourceMapper.deleteResource(logicDeleteDTO);
    }

    /**
     * 上传作业
     * @param classId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadHomework(UploadHomeworkDTO uploadHomeworkDTO, Long classId) {


        List<Homework> homeworkList =new ArrayList<>();

        //循环将数据加到POList
        for (int i = 0; i< uploadHomeworkDTO.getHomeworkDTOList().size(); i++){
            Homework homeworkPO = new Homework();
            Long homeworkId = uniqueIdHelper.snowIdBuild();

            homeworkPO.setClassId(classId);
            homeworkPO.setDeadline(uploadHomeworkDTO.getHomeworkDTOList().get(i).getDeadline());
            homeworkPO.setUrl(getFileURL(uploadHomeworkDTO.getHomeworkDTOList().get(i).getMultipartFile()));
            homeworkPO.setWorkName(uploadHomeworkDTO.getHomeworkDTOList().get(i).getWorkName());
            homeworkPO.setWorkType(uploadHomeworkDTO.getHomeworkDTOList().get(i).getWorkType());
            homeworkPO.setHomeworkId(homeworkId);

            homeworkList.add(homeworkPO);

        }

            homeworkMapper.uploadHomework(homeworkList);


    }

    /**
     * 作业回显
     * @param classId
     * @return
     */
    @Override
    public List<HomeworkVO> queryHomework(Long classId) {

        List<HomeworkVO> homeworkVOList = homeworkMapper.queryHomework(classId);

        return homeworkVOList;
    }

    /**
     * 学生上传答案
     * @param uploadSelfHomeworkDTO
     */
    @Override
    public void uploadMyAnswer(UploadSelfHomeworkDTO uploadSelfHomeworkDTO) {

        List<StuAnswer> stuAnswerList = new ArrayList<>();
        ContentEntity contentEntity = new ContentEntity();
        Long contentId = uniqueIdHelper.snowIdBuild();

        contentEntity.setContentId(contentId);
        contentEntity.setContent(uploadSelfHomeworkDTO.getSelfHomeworkDTOList().get(0).getContent());
        for (SelfHomeworkDTO selfHomeworkDTO : uploadSelfHomeworkDTO.getSelfHomeworkDTOList()){

            StuAnswer stuAnswer = new StuAnswer();

            stuAnswer.setAnswerId(uniqueIdHelper.snowIdBuild());
            stuAnswer.setStudentId(selfHomeworkDTO.getStudentId());
            stuAnswer.setContentId(contentId);
            stuAnswer.setClassId(stuAnswer.getClassId());
            stuAnswer.setHomeworkId(selfHomeworkDTO.getHomeworkId());
            stuAnswer.setUrl(getFileURL(selfHomeworkDTO.getMultipartFile()));


        }
        myHomeworkMapper.uploadMyHomework(stuAnswerList);
        myHomeworkMapper.uploadContent(contentEntity);
    }

    @Override
    public List<AnswerVO> queryAllAnswer(Long classId,Long homeworkId) {

        answerMapper.queryAllAnswer();
        return null;
    }


    /**
     * 获取上传文件的url
      */
    private String getFileURL(MultipartFile file){

        //文件入盘
        if (file.isEmpty()){
            return null;
        }
        System.out.println(file);
        return UploadUtil.upload(file);
    }
}
