package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpProductSpecificationDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.ProductSpecification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpProductSpecificationPropertyMapper.class})
public interface MrktpProductSpecificationMapper extends MrktpBaseMapper<ProductSpecification, MrktpProductSpecificationDto> {
}
