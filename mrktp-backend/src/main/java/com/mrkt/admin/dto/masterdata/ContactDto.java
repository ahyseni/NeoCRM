package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "1", notes = "Identifier of the tenant/company")
    private Long tenantId;

    @ApiModelProperty(example = "2", notes = "Identifier of the user who created the contact")
    private Long createdBy;

    @ApiModelProperty(example = "John", notes = "Contact's first name")
    private String firstName;

    @ApiModelProperty(example = "Doe", notes = "Contact's last name")
    private String lastName;

    @ApiModelProperty(example = "john.doe@customer.com", notes = "Contact's email")
    private String email;

    @ApiModelProperty(example = "+123456789", notes = "Contact's phone number")
    private String phone;

    @ApiModelProperty(example = "123 Main St, City", notes = "Contact's address")
    private String address;
}
