package com.mrkt.admin.dto.masterdata;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public abstract class MultiLanguageBaseDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "nameEN")
    private String nameEN;

    @ApiModelProperty(example = "nameNL")
    private String nameNL;

    @ApiModelProperty(example = "nameGR")
    private String nameGR;

    @ApiModelProperty(example = "nameDE")
    private String nameDE;

    @ApiModelProperty(example = "nameAL")
    private String nameAL;

    @ApiModelProperty(example = "nameFR")
    private String nameFR;

    public MultiLanguageBaseDto(String id, String nameEN, String nameNL, String nameGR, String nameDE, String nameAL, String nameFR, Boolean active) {
        super(id, nameEN, nameNL, nameGR, nameDE, nameAL, nameFR, active);
        this.nameNL = nameNL;
        this.nameGR = nameGR;
        this.nameAL = nameAL;
        this.nameDE = nameDE;
        this.nameFR = nameFR;
        this.nameEN = nameEN;

    }
}
