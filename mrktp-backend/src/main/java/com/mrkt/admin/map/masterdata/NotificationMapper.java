package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.NotificationDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper extends MrktpBaseMapper<Notification, NotificationDto> {
}
