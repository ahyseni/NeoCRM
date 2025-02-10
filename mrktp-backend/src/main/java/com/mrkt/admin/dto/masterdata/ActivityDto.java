package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ActivityDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "1", notes = "Identifier of the tenant/company")
    private Long tenantId;

    @ApiModelProperty(example = "2", notes = "Identifier of the user who performed the activity")
    private Long userId;

    @ApiModelProperty(example = "Call", notes = "Type of activity (e.g., Call, Meeting, Email)")
    private String activityType;

    @ApiModelProperty(example = "Discussed the project details", notes = "Description of the activity")
    private String description;

    @ApiModelProperty(example = "2025-04-01T14:30:00Z", notes = "Date and time when the activity took place")
    private ZonedDateTime activityDate;
}
