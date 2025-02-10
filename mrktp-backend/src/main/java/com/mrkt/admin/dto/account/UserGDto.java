package com.mrkt.admin.dto.account;

import com.mrkt.admin.dto.order.OrderDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserGDto extends AccountBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "surname")
    private String surname;

    @ApiModelProperty(example = "email")
    private String email;

    @ApiModelProperty(example = "phone")
    private String phone;

    @ApiModelProperty(example = "mobile")
    private String mobile;

    @ApiModelProperty(example = "addresses")
    private Set<AddressDto> addresses;

    @ApiModelProperty(example = "userType")
    private Set<UserTypeDto> userTypeDtos;

    @ApiModelProperty(example = "orders")
    private Set<OrderDto> orderDtos;
}
