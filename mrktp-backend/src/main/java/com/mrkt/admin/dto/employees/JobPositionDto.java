package com.mrkt.admin.dto.employees;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrkt.admin.dto.masterdata.MasterdataBaseDto;
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
public class JobPositionDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "name")
    private String name;


    @ApiModelProperty(example = "employee")
    private EmployeeDto employee;
}
