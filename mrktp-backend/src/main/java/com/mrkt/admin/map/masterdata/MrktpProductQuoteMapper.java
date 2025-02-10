package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpProductQuoteDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.model.masterdata.ProductQuote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpProductMapper.class})
public interface MrktpProductQuoteMapper extends MrktpBaseMapper<ProductQuote, MrktpProductQuoteDto> {
}
