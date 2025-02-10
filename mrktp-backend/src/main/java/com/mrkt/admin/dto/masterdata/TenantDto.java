package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TenantDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "Acme Corp", notes = "Name of the tenant/company")
    private String tenantName;

    @ApiModelProperty(example = "Enterprise", notes = "Type of license assigned")
    private String licenseType;

    @ApiModelProperty(example = "50", notes = "Maximum number of users allowed")
    private Integer licenseLimits;

    @ApiModelProperty(example = "2025-01-01", notes = "Subscription start date")
    private LocalDate subscriptionStart;

    @ApiModelProperty(example = "2025-12-31", notes = "Subscription end date")
    private LocalDate subscriptionEnd;
}
