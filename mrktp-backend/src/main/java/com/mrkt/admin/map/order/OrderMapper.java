package com.mrkt.admin.map.order;

import com.mrkt.admin.dto.order.OrderDto;
import com.mrkt.admin.map.MrktpBaseMapper;
import com.mrkt.admin.map.account.MerchantMapper;
import com.mrkt.admin.map.masterdata.UserMapper;
import com.mrkt.admin.map.masterdata.AccountMapper;
import com.mrkt.admin.model.order.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MerchantMapper.class, UserMapper.class, OrderItemsMapper.class, OrderHistoryMapper.class, AccountMapper.class})
public interface OrderMapper extends MrktpBaseMapper<Order, OrderDto> {


}
