package com.mrkt.admin.dto.masterdata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DealDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "1", notes = "Identifier of the tenant/company")
    private Long tenantId;

    @ApiModelProperty(example = "3", notes = "Identifier of the primary contact associated with the deal")
    private Long contactId;

    @ApiModelProperty(example = "Big Sale", notes = "Name of the deal")
    private String dealName;

    @ApiModelProperty(example = "This is a description of the deal.", notes = "Detailed description of the deal")
    private String description;

    @ApiModelProperty(example = "Negotiation", notes = "Current stage of the deal")
    private String stage;

    @ApiModelProperty(example = "15000.00", notes = "Monetary amount associated with the deal")
    private BigDecimal amount;

    @ApiModelProperty(example = "2025-06-30", notes = "Expected close date for the deal")
    private LocalDate closeDate;
}
