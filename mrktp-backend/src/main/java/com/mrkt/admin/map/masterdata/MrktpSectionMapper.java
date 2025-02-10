package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpSectionDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.Section;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpGroupMapper.class, MrktpSectionCategoryMapper.class})
public interface MrktpSectionMapper extends MrktpBaseMapper<Section, MrktpSectionDto> {


}
