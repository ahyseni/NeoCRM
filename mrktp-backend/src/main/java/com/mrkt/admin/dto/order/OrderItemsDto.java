package com.mrkt.admin.dto.order;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrkt.admin.dto.masterdata.ContactDto;
import com.mrkt.admin.dto.masterdata.MasterdataBaseDto;
import com.mrkt.admin.dto.masterdata.MrktpProductDto;
import com.mrkt.admin.model.masterdata.Product;
import com.mrkt.admin.model.order.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder
public class OrderItemsDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "account_name")
    private String name;

    @ApiModelProperty(example = "ean_code")
    private String eancode;

    @ApiModelProperty(example = "ian_code")
    private String iancode;

    @ApiModelProperty(example = "description")
    private String description;


    @ApiModelProperty(example = "quantity")
    private int quantity;


    @ApiModelProperty(example = "net_value")
    private double net_value;

    @ApiModelProperty(example = "value_withvat")
    private double value_withvat;

    @ApiModelProperty(example = "rate_value")
    private double rate_value;

    @ApiModelProperty(example = "fix_rate")
    private double fix_rate;

    @ApiModelProperty(example = "discount")
    private double discount;


    @ApiModelProperty(example = "discount")
    private Order order;

    @ApiModelProperty(example = "product")
    private MrktpProductDto product;

}
