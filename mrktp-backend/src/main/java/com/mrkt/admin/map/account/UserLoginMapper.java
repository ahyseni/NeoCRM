package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.UserLoginDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.account.UserLogin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserGMapper.class})
public interface UserLoginMapper extends MrktpBaseMapper<UserLogin, UserLoginDto> {
}
