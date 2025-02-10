package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.UserDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.account.*;
import com.mrkt.admin.map.employee.EmployeeMapper;
import com.mrkt.admin.model.masterdata.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserLoginMapper.class, EmployeeMapper.class, UserTypeMapper.class, UserGroupsMapper.class, MerchantMapper.class, AddressMapper.class})
public interface UserMapper extends MrktpBaseMapper<User, UserDto> {
}
