package com.mrkt.admin.dto.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MerchantRatingDto extends AccountBaseDto {

    @ApiModelProperty(example = "rating")
    private String rating;

    @ApiModelProperty(example = "comment")
    private String comment;

    @ApiModelProperty(example = "merchantId")
    private String merchantId;

}
