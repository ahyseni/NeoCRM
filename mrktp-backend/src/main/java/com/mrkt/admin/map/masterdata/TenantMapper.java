package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.TenantDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.Tenant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TenantMapper extends MrktpBaseMapper<Tenant, TenantDto> {
}
