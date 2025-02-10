package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.MerchantPropertyDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.account.MerchantProperty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MerchantMapper.class})
public interface MerchantPropertyMapper extends MrktpBaseMapper<MerchantProperty, MerchantPropertyDto> {
}
