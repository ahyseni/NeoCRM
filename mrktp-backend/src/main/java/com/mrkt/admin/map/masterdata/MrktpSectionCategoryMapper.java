package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpSectionCategoriesDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.SectionCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpSectionMapper.class, MrktpSectionPropertyMapper.class})
public interface MrktpSectionCategoryMapper extends MrktpBaseMapper<SectionCategory, MrktpSectionCategoriesDto> {
}
