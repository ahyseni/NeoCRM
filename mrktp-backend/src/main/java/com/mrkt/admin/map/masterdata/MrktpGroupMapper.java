package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpGroupDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpSectionMapper.class})
public interface MrktpGroupMapper extends MrktpBaseMapper<Group, MrktpGroupDto> {


}
