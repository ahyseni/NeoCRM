package com.mrkt.admin.dto.account;

import com.mrkt.admin.model.account.UserGroups;
import com.mrkt.admin.model.account.UserRoles;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto extends AccountBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "userRoles")
    private List<UserRolesDto> userRolesDtos = new ArrayList<>();


    @ApiModelProperty(example = "userGroups")
    private List<UserGroupsDto> userGroupsDtos = new ArrayList<>();
}
