package com.mrkt.admin.dto.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserGroupsDto extends AccountBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "userDto")
    private UserGDto userDto;

    @ApiModelProperty(example = "userGroupDto")
    private UserGroupDto userGroupDto;
}
