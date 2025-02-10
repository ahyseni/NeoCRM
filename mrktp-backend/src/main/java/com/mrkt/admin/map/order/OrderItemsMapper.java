package com.mrkt.admin.map.order;

import com.mrkt.admin.dto.order.OrderItemsDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.masterdata.MrktpProductMapper;
import com.mrkt.admin.map.masterdata.MrktpSectionMapper;
import com.mrkt.admin.model.order.OrderItems;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, MrktpProductMapper.class})
public interface OrderItemsMapper extends MrktpBaseMapper<OrderItems, OrderItemsDto> {


}
