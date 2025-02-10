package com.mrkt.admin.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mrkt.admin.enums.OpportunityStage;
import com.mrkt.admin.enums.OpportunityType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder
public class OpportunityDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "code")
    private String code;

    @ApiModelProperty(example = "description")
    private String description;

    @ApiModelProperty(example = "description")
    private OpportunityType opportunityType;

    @ApiModelProperty(example = "description")
    private OpportunityStage opportunityStage;

    @ApiModelProperty(example = "amount")
    private String amount;

    @ApiModelProperty(example = "probability")
    private String probability;

    @ApiModelProperty(example = "expectedRevenue")
    private String expectedRevenue;

    @ApiModelProperty(example = "phone")
    private String phone;

    @ApiModelProperty(example = "closeOn")
    private String closeOn;

    @ApiModelProperty(example = "contacts")
    private List<ContactDto> contacts = new ArrayList<>();


}
