package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "1", notes = "Identifier of the tenant/company")
    private Long tenantId;

    @ApiModelProperty(example = "johndoe", notes = "Username for the user")
    private String username;

    @ApiModelProperty(example = "john.doe@example.com", notes = "User email address")
    private String email;

    // Typically you would not expose password hash in a DTO,
    // so you may want to remove or handle it securely.
    @ApiModelProperty(hidden = true)
    private String passwordHash;

    @ApiModelProperty(example = "Admin", notes = "Role of the user within the tenant")
    private String role;

    @ApiModelProperty(example = "true", notes = "Flag indicating if the user is active")
    private Boolean isActive;
}
