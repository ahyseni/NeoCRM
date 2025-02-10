package com.mrkt.admin.dto.account;

import com.mrkt.admin.dto.masterdata.MrktpProductDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MerchantDto extends AccountBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "surname")
    private String surname;

    @ApiModelProperty(example = "kvk")
    private String kvk;

    @ApiModelProperty(example = "email")
    private String email;

    @ApiModelProperty(example = "phone")
    private String phone;

    @ApiModelProperty(example = "mobile")
    private String mobile;

    @ApiModelProperty(example = "userId")
    private String userId;


    @ApiModelProperty(example = "Merchant Products")
    private Set<MrktpProductDto> mrktpProductDtos;

}
