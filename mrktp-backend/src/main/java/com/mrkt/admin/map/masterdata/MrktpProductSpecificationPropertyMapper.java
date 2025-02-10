package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpProductSpecificationPropertyDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.ProductSpecificationProperty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpProductSpecificationMapper.class})
public interface MrktpProductSpecificationPropertyMapper extends MrktpBaseMapper<ProductSpecificationProperty, MrktpProductSpecificationPropertyDto> {
}
