package com.mrkt.admin.dto.client;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrkt.admin.dto.masterdata.MasterdataBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AccessType;
import javax.persistence.Column;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder
public class ClientDto extends MasterdataBaseDto {


    @ApiModelProperty(example = "client_name")
    private String name;

    @ApiModelProperty(example = "client_code")
    private String code;

    @ApiModelProperty(example = "description")
    private String description;


    @ApiModelProperty(example = "country_name")
    private String country_name;

    @ApiModelProperty(example = "account_Type")
    private AccessType account_Type;

    @ApiModelProperty(example = "vat_number")
    private String vat;

    @ApiModelProperty(example = "city")
    private String city;

    @ApiModelProperty(example = "postcode")
    private String postcode;

    @ApiModelProperty(example = "phone_number")
    private String phone;

    @ApiModelProperty(example = "email")
    private String email;


    @ApiModelProperty(example = "address")
    private String address;


}
