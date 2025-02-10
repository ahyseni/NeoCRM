package com.mrkt.admin.dto.order;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrkt.admin.dto.masterdata.ContactDto;
import com.mrkt.admin.dto.masterdata.MasterdataBaseDto;
import com.mrkt.admin.enums.OrderStatus;
import com.mrkt.admin.model.order.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder
public class OrderHistoryDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "code")
    private String code;

    @ApiModelProperty(example = "orderStatus")
    private OrderStatus orderStatus;

    @ApiModelProperty(example = "orderDto")
    private OrderDto orderDto;
}
