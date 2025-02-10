package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MrktpSectionCategoriesDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "code")
    private String code;

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "description")
    private String description;

    @ApiModelProperty(example = "extrafield1")
    private String extrafield1;

    @ApiModelProperty(example = "extrafield2")
    private String extrafield2;

    @ApiModelProperty(example = "sectionId")
    private String sectionId;

    @ApiModelProperty(example = "Section Properties")
    private Set<MrktpSectionPropertyDto> mrktpSectionPropertyDtos;

}