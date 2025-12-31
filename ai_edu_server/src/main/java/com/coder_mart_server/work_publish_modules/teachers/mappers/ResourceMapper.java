package com.coder_mart_server.work_publish_modules.teachers.mappers;


import com.coder_mart_server.work_publish_modules.teachers.pojo.dto.LogicDeleteDTO;
import com.coder_mart_server.work_publish_modules.teachers.pojo.entity.ResourceEntity;
import com.coder_mart_server.work_publish_modules.teachers.pojo.vo.ResourceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper {

     /**
      * 上传资源
      */
     void uploadResource(@Param("reList") List<ResourceEntity> resourceEntityList);

     /**
      * 回显
      * @return
      */
     List<ResourceVO> research(@Param("classId") Long id);

     /**
      * 逻辑删除
      * @param logicDeleteDTO
      */
     void deleteResource(@Param("dto") LogicDeleteDTO logicDeleteDTO);
}
