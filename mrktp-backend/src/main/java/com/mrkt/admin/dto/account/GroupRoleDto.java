package com.mrkt.admin.dto.account;

import com.mrkt.admin.model.account.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupRoleDto extends AccountBaseDto {

    @ApiModelProperty(example = "name")
    private String name;


    @ApiModelProperty(example = "role")
    private RoleDto roleDto;


    @ApiModelProperty(example = "surname")
    private UserGroupDto userGroupDto;
}
