package com.mrkt.admin.dto.account;

import com.mrkt.admin.model.account.GroupRole;
import com.mrkt.admin.model.account.UserGroups;
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
public class UserGroupDto extends AccountBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "userGroups")
    private List<UserGroupsDto> userGroups = new ArrayList<>();


    @ApiModelProperty(example = "groupRoles")
    private List<GroupRoleDto> groupRoles = new ArrayList<>();
}
