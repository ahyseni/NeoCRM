package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "1", notes = "Identifier of the tenant/company")
    private Long tenantId;

    @ApiModelProperty(example = "2", notes = "Identifier of the user assigned to the task")
    private String assignedTo;

    @ApiModelProperty(example = "Follow-up Call", notes = "Title of the task")
    private String title;

    @ApiModelProperty(example = "Call the customer to discuss details", notes = "Description of the task")
    private String description;

    @ApiModelProperty(example = "2025-05-15", notes = "Due date for the task")
    private LocalDate dueDate;

    @ApiModelProperty(example = "Pending", notes = "Current status of the task")
    private String status;
}
