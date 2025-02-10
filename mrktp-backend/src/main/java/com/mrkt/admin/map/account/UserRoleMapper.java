package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.UserRolesDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.masterdata.UserMapper;
import com.mrkt.admin.model.account.UserRoles;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, UserGMapper.class})
public interface UserRoleMapper extends MrktpBaseMapper<UserRoles, UserRolesDto> {


}
