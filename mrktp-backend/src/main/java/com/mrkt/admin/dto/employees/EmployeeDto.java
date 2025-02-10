package com.mrkt.admin.dto.employees;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrkt.admin.dto.account.UserGDto;
import com.mrkt.admin.dto.masterdata.MasterdataBaseDto;
import com.mrkt.admin.enums.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder
public class EmployeeDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "first_name")
    private String name;

    @ApiModelProperty(example = "userDto")
    private UserGDto userDto;

    @ApiModelProperty(example = "gender")
    private Gender gender;

    @ApiModelProperty(example = "lastName")
    private String lastName;

    @ApiModelProperty(example = "email")
    private String email;

    @ApiModelProperty(example = "employee_code")
    private String code;

    @ApiModelProperty(example = "city")
    private String city;

    @ApiModelProperty(example = "postcode")
    private String postcode;

    @ApiModelProperty(example = "phone_number")
    private String phone;


    @ApiModelProperty(example = "birthdate")
    private String birthdate;


    @ApiModelProperty(example = "hiringOn")
    private String hiringOn;


    @ApiModelProperty(example = "address")
    private String address;


    @ApiModelProperty(example = "extrafield1")
    private String extrafield1;


    @ApiModelProperty(example = "extrafield2")
    private String extrafield2;

    @ApiModelProperty(example = "employee")
    private DepartmentDto department;

    @ApiModelProperty(example = "employee")
    private JobPositionDto jobPosition;


}
