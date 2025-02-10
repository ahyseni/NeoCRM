package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.MerchantDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.masterdata.MrktpProductMapper;
import com.mrkt.admin.model.account.Merchant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MerchantPropertyMapper.class, MerchantRatingMapper.class, MrktpProductMapper.class, UserGMapper.class})
public interface MerchantMapper extends MrktpBaseMapper<Merchant, MerchantDto> {
}
