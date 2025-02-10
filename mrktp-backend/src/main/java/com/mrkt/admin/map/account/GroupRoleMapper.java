package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.GroupRoleDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.masterdata.MrktpSectionMapper;
import com.mrkt.admin.model.account.GroupRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserGroupMapper.class, RoleMapper.class})
public interface GroupRoleMapper extends MrktpBaseMapper<GroupRole, GroupRoleDto> {


}
