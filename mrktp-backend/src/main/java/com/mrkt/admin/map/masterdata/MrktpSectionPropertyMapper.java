package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpSectionPropertyDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.SectionProperty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpSectionCategoryMapper.class, MrktpProductMapper.class})
public interface MrktpSectionPropertyMapper extends MrktpBaseMapper<SectionProperty, MrktpSectionPropertyDto> {
}
