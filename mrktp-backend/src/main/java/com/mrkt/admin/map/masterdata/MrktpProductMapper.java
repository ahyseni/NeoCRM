package com.mrkt.admin.map.masterdata;

import com.mrkt.admin.dto.masterdata.MrktpProductDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.account.MerchantMapper;
import com.mrkt.admin.map.order.OrderItemsMapper;
import com.mrkt.admin.map.order.OrderMapper;
import com.mrkt.admin.model.masterdata.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MrktpSectionMapper.class, MerchantMapper.class, OrderItemsMapper.class, MrktpProductSpecificationMapper.class, MrktpProductQuoteMapper.class, MrktpProductPropertyMapper.class})
public interface MrktpProductMapper extends MrktpBaseMapper<Product, MrktpProductDto> {
}
