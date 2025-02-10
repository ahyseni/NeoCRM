package com.mrkt.admin.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder
public class MrktpGroupDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "code")
    private String code;

    @ApiModelProperty(example = "description")
    private String description;


    @ApiModelProperty(example = "extrafield1")
    private String extrafield1;


    @ApiModelProperty(example = "extrafield2")
    private String extrafield2;

    @ApiModelProperty(example = "sections")
    private List<MrktpSectionDto> sectionDtos = new ArrayList<>();


}
