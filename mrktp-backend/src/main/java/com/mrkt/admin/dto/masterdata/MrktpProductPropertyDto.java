package com.mrkt.admin.dto.masterdata;

import com.mrkt.admin.dto.MrktpBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MrktpProductPropertyDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "picture")
    private String picture;

    @ApiModelProperty(example = "productID")
    private String productId;

}
