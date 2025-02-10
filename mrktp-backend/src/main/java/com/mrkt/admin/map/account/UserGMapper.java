package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.UserGDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.account.*;
import com.mrkt.admin.map.employee.EmployeeMapper;
import com.mrkt.admin.model.account.UserG;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserLoginMapper.class, EmployeeMapper.class, UserTypeMapper.class, UserGroupsMapper.class, MerchantMapper.class, AddressMapper.class})
public interface UserGMapper extends MrktpBaseMapper<UserG, UserGDto> {

}