package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.UserGroupDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.masterdata.MrktpSectionMapper;
import com.mrkt.admin.model.account.UserGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserGroupsMapper.class, GroupRoleMapper.class})
public interface UserGroupMapper extends MrktpBaseMapper<UserGroup, UserGroupDto> {


}
