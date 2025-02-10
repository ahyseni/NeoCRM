package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.DealDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.Deal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealMapper extends MrktpBaseMapper<Deal, DealDto> {
}
