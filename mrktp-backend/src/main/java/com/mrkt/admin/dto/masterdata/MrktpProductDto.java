package com.mrkt.admin.dto.masterdata;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MrktpProductDto extends MasterdataBaseDto {


    @ApiModelProperty(example = "eancode")
    private String eancode;

    @ApiModelProperty(example = "iancode")
    private String iancode;

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "createdDateTime")
    private Instant createdDateTime;

    @ApiModelProperty(example = "merchantCode")
    private String merchantcode;

    @ApiModelProperty(example = "validFrom")
    private Instant validFromDateTime;

    @ApiModelProperty(example = "validTo")
    private Instant validToDateTime;

    @ApiModelProperty(example = "description")
    private String description;

    @ApiModelProperty(example = "stock")
    private String stock;

    @ApiModelProperty(example = "price")
    private String price;

    @ApiModelProperty(example = "extrafield1")
    private String extrafield1;

    @ApiModelProperty(example = "extrafield2")
    private String extrafield2;

    @ApiModelProperty(example = "userMail")
    private String userEmail;

    @ApiModelProperty(example = "Brand")
    private String brand;

    @ApiModelProperty(example = "mrktpSectionPropertyID")
    private String sectionPropertyId;

    @ApiModelProperty(example = "properties")
    private List<MrktpProductPropertyDto> properties;

    @ApiModelProperty(example = "messages")
    private List<MrktpProductQuoteDto> messages;

    @ApiModelProperty(example = "specifications")
    private List<MrktpProductSpecificationDto> specifications;
}
