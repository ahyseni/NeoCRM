package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.UserTypeDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.masterdata.UserMapper;
import com.mrkt.admin.model.account.UserType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserGMapper.class})
public interface UserTypeMapper extends MrktpBaseMapper<UserType, UserTypeDto> {
}
