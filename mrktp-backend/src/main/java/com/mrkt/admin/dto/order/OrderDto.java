package com.mrkt.admin.dto.order;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrkt.admin.dto.account.MerchantDto;
import com.mrkt.admin.dto.masterdata.AccountDto;
import com.mrkt.admin.dto.masterdata.MasterdataBaseDto;
import com.mrkt.admin.dto.masterdata.UserDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder
public class OrderDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "account_name")
    private String name;


    @ApiModelProperty(example = "valid_from")
    private Instant validFromDateTime;

    @ApiModelProperty(example = "valid_to")
    private Instant validToDateTime;


    @ApiModelProperty(example = "comment")
    private String comment;


    @ApiModelProperty(example = "totalAmount")
    private double totalAmount;


    @ApiModelProperty(example = "requiredOn")
    private String requiredOn;


    @ApiModelProperty(example = "shippedOn")
    private String shippedOn;


    @ApiModelProperty(example = "code")
    private String code;

    @ApiModelProperty(example = "order")
    private List<OrderHistoryDto> orderHistoryDtoList = new ArrayList<>();

    @ApiModelProperty(example = "orderItemsDtoList")
    private List<OrderItemsDto> orderItemsDtoList = new ArrayList<>();

    @ApiModelProperty(example = "merchantDto")
    private MerchantDto merchantDto;

    @ApiModelProperty(example = "accountDto")
    private AccountDto accountDto;

    @ApiModelProperty(example = "userDto")
    private UserDto userDto;


}
