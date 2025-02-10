package com.mrkt.admin.dto.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto extends AccountBaseDto {

    @ApiModelProperty(example = "email")
    private String email;

    @ApiModelProperty(example = "pwd")
    private String pwd;

    @ApiModelProperty(example = "userId")
    private String userId;
}
