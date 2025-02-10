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
public class MerchantPropertyDto extends AccountBaseDto {

    @ApiModelProperty(example = "creditStatus")
    private String creditStatus;

    @ApiModelProperty(example = "appliedRate")
    private String appliedRate;

    @ApiModelProperty(example = "appliedFix")
    private String appliedFix;

    @ApiModelProperty(example = "appliedRateActive")
    private boolean appliedRateActive;

    @ApiModelProperty(example = "appliedFixActive")
    private String phone;

    @ApiModelProperty(example = "merchantId")
    private String merchantId;


}
