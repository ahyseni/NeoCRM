package com.mrkt.admin.map.order;

import com.mrkt.admin.dto.order.OrderHistoryDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.masterdata.MrktpSectionMapper;
import com.mrkt.admin.model.order.OrderHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface OrderHistoryMapper extends MrktpBaseMapper<OrderHistory, OrderHistoryDto> {


}
