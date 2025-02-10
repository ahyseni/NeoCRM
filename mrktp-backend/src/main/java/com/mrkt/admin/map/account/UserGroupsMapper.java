package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.UserGroupsDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.account.UserGroups;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserGroupMapper.class, UserGMapper.class})
public interface UserGroupsMapper extends MrktpBaseMapper<UserGroups, UserGroupsDto> {


}
