package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.StatusDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpSectionMapper.class})
public interface StatusMapper extends MrktpBaseMapper<Status, StatusDto> {


}
