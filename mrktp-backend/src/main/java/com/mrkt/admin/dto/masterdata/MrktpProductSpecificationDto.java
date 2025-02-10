package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MrktpProductSpecificationDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "specification-properties")
    private List<MrktpProductSpecificationPropertyDto> specificationProperties;


    @ApiModelProperty(example = "productID")
    private String productID;
}
