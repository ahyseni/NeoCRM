package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AuditLogDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "1", notes = "Identifier of the tenant/company")
    private Long tenantId;

    @ApiModelProperty(example = "2", notes = "Identifier of the user who performed the action")
    private Long userId;

    @ApiModelProperty(example = "UPDATE", notes = "Action performed")
    private String action;

    @ApiModelProperty(example = "Updated contact details", notes = "Additional details about the action")
    private String details;

    @ApiModelProperty(example = "2025-04-01T15:00:00Z", notes = "Timestamp of when the action occurred")
    private ZonedDateTime actionDate;
}
