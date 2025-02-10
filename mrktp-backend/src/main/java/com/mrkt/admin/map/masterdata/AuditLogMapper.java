package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.AuditLogDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.UserIdMapper;
import com.mrkt.admin.model.masterdata.AuditLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserIdMapper.class})
public interface AuditLogMapper extends MrktpBaseMapper<AuditLog, AuditLogDto> {
}
