package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.ActivityDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.UserIdMapper;
import com.mrkt.admin.model.masterdata.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserIdMapper.class})
public interface ActivityMapper extends MrktpBaseMapper<Activity, ActivityDto> {
}
