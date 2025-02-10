package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.TaskDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.UserIdMapper;
import com.mrkt.admin.model.masterdata.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserIdMapper.class})
public interface TaskMapper extends MrktpBaseMapper<Task, TaskDto> {

}
