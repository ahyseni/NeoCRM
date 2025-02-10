package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.RoleDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.masterdata.MrktpSectionMapper;
import com.mrkt.admin.model.account.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserGroupMapper.class, UserRoleMapper.class})
public interface RoleMapper extends MrktpBaseMapper<Role, RoleDto> {


}
