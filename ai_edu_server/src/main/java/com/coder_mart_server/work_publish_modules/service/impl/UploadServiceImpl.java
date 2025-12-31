package com.coder_mart_server.work_publish_modules.service.impl;


import com.coder_mart_server.public_modules.helppers.UniqueIdHelper;
import com.coder_mart_server.public_modules.properties.SnowProperties;
import com.coder_mart_server.security.security_modules.authenticator.context.ISecurity;
import com.coder_mart_server.work_publish_modules.service.UploadService;
import com.coder_mart_server.work_publish_modules.teachers.mappers.ClassTeacherMapper;
import com.coder_mart_server.work_publish_modules.teachers.mappers.HomeworkMapper;
import com.coder_mart_server.work_publish_modules.teachers.mappers.ResourceMapper;

import com.coder_mart_server.work_publish_modules.teachers.pojo.dto.LogicDeleteDTO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.dto.UploadHomeworkDTO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.entity.Homework;
import com.coder_mart_server.work_publish_modules.teachers.pojo.entity.ResourceEntity;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.HomeworkVO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.ResourceVO;
import com.coder_mart_server.work_publish_modules.util.UploadUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
    @Resource
    private SnowProperties snowProperties;
    @Resource
    private UniqueIdHelper uniqueIdHelper;
    /**
     * 老师上传多个文件
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadResources(List<MultipartFile>multipartFileList,List<String> resourceName,Long classId) {

        List<String> MultiURLS = getFileURL(multipartFileList);



        List<ResourceEntity> resourceEntityList = new ArrayList<>();
        for (int i = 0;i<MultiURLS.size();i++){
            ResourceEntity resourceEntity = new ResourceEntity();
            Long resourceId = uniqueIdHelper.snowIdBuild();

            resourceEntity.setResourceId(resourceId);
            resourceEntity.setResourceName(resourceName.get(i));
            resourceEntity.setUrl(MultiURLS.get(i));
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
     * 逻辑删除
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
     * @param multipartFileList
     * @param uploadHomeworkDTO
     * @param classId
     */
    @Override
    public void uploadHomework(List<MultipartFile> multipartFileList, List<UploadHomeworkDTO> uploadHomeworkDTO,
                               Long classId) {

        List<String> urlList = getFileURL(multipartFileList);

        List<Homework> homeworkList =new ArrayList<>();

        //循环将数据加到POList
        for (int i = 0;i<uploadHomeworkDTO.size();i++){
            Homework homeworkPO = new Homework();
            Long homeworkId = uniqueIdHelper.snowIdBuild();

            homeworkPO.setClassId(classId);
            homeworkPO.setDeadline(uploadHomeworkDTO.get(i).getDeadline());
            homeworkPO.setUrl(urlList.get(i));
            homeworkPO.setWorkName(uploadHomeworkDTO.get(i).getWorkName());
            homeworkPO.setWorkType(uploadHomeworkDTO.get(i).getWorkType());
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
     * 获取所有上传文件的url
      */
    private List<String> getFileURL(List<MultipartFile> files ){


        List<String> list = new ArrayList<>();
        //文件入盘
        for (MultipartFile file : files){

            String url = UploadUtil.upload(file);

            list.add(url);
        }
        return list;
    }
}
