package com.mrkt.admin.dto.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto extends AccountBaseDto {


    @ApiModelProperty(example = "city")
    private String city;

    @ApiModelProperty(example = "country")
    private String country;

    @ApiModelProperty(example = "street")
    private String street;

    @ApiModelProperty(example = "postcode")
    private String postcode;

    @ApiModelProperty(example = "houseNumber")
    private String houseNumber;

    @ApiModelProperty(example = "userId")
    private String userId;

}
