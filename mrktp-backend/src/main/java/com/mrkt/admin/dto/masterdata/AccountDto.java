package com.mrkt.admin.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AccessType;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder
public class AccountDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "account_name")
    private String name;

    @ApiModelProperty(example = "account_code")
    private String code;

    @ApiModelProperty(example = "description")
    private String description;

    @ApiModelProperty(example = "country_name")
    private String country_name;

    @ApiModelProperty(example = "account_Type")
    private AccessType account_Type;

    @ApiModelProperty(example = "vat")
    private String vat;

    @ApiModelProperty(example = "city")
    private String city;

    @ApiModelProperty(example = "postcode")
    private String postcode;

    @ApiModelProperty(example = "phone")
    private String phone;

    @ApiModelProperty(example = "email")
    private String email;


    @ApiModelProperty(example = "address")
    private String address;

    @ApiModelProperty(example = "contactList")
    private List<ContactDto> contacts = new ArrayList<>();


}
