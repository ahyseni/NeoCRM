package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "1", notes = "Identifier of the tenant/company")
    private Long tenantId;

    @ApiModelProperty(example = "2", notes = "Identifier of the user receiving the notification")
    private Long userId;

    @ApiModelProperty(example = "Email", notes = "Type of notification")
    private String type;

    @ApiModelProperty(example = "You have a new task assigned", notes = "Notification message")
    private String message;

    @ApiModelProperty(example = "false", notes = "Flag indicating if the notification has been read")
    private Boolean isRead;
}
