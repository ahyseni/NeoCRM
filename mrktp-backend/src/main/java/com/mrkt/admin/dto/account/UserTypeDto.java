package com.mrkt.admin.dto.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserTypeDto extends AccountBaseDto {


    @ApiModelProperty(example = "type")
    private String type;

    @ApiModelProperty(example = "typeDescription")
    private String typeDescription;

    @ApiModelProperty(example = "userId")
    private String userId;

}
