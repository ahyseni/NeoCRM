package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpProductPropertyDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.ProductProperty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpProductMapper.class})
public interface MrktpProductPropertyMapper extends MrktpBaseMapper<ProductProperty, MrktpProductPropertyDto> {
}
