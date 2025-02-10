package com.mrkt.admin.dto.masterdata;

import com.mrkt.admin.dto.MrktpBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MasterdataBaseDto extends MrktpBaseDto {

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

    @ApiModelProperty(example = "active")
    private Boolean active;


    public MasterdataBaseDto(String id, String nameEN,
                             String nameNL, String nameGR, String nameDE, String nameAL, String nameFR, Boolean active) {
        super(id);
        this.nameEN = nameEN;
        this.nameNL = nameNL;
        this.nameGR = nameGR;
        this.nameDE = nameDE;
        this.nameAL = nameAL;
        this.nameFR = nameFR;
        this.active = active;
    }
}
