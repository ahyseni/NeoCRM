package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class MrktpSectionPropertyDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "section_code")
    private String section_code;

    @ApiModelProperty(example = "section_name")
    private String section_name;


    @ApiModelProperty(example = "description")
    private String description;

    @ApiModelProperty(example = "extrafield1")
    private String extrafield1;

    @ApiModelProperty(example = "extrafield2")
    private String extrafield2;

    @ApiModelProperty(example = "mrktpSectionCategoriesID")
    private String mrktpSectionCategoriesID;

    @ApiModelProperty(example = "product")
    private Set<MrktpProductDto> productsDto;

}