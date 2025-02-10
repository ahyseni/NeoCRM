package com.mrkt.admin.map.account;

import com.mrkt.admin.dto.account.MerchantRatingDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.account.MerchantRating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MerchantMapper.class})
public interface MerchantRatingMapper extends MrktpBaseMapper<MerchantRating, MerchantRatingDto> {
}
