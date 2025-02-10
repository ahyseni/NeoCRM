package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.AddressDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.account.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserGMapper.class})
public interface AddressMapper extends MrktpBaseMapper<Address, AddressDto> {
}
