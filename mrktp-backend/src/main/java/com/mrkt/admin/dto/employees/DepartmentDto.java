package com.mrkt.admin.dto.employees;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrkt.admin.dto.masterdata.MasterdataBaseDto;
import com.mrkt.admin.enums.DepartmentType;
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
public class DepartmentDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "account_name")
    private String name;


    @ApiModelProperty(example = "departmentType")
    private DepartmentType departmentType;


    @ApiModelProperty(example = "employeeDto")
    private EmployeeDto employeeDto;

}
