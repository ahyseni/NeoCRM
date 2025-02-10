package com.mrkt.admin.dto.adds;

import com.mrkt.admin.dto.MrktpBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class MrktpHeadingSubBaseDto extends MrktpBaseDto {

    @ApiModelProperty(example = "")
    private String heading_subcode;

    @ApiModelProperty(example = "")
    private String name;

    @ApiModelProperty(example = "")
    private List<MrktpAdvertiseDto> advertises = new ArrayList<>();
}
