package com.mrkt.admin.dto.adds;

import com.mrkt.admin.dto.MrktpBaseDto;
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
public class MrktpAdvertiseBaseDto extends MrktpBaseDto {

    @ApiModelProperty(example = "code")
    private String code;

    @ApiModelProperty(example = "title")
    private String title;

    @ApiModelProperty(example = "createddateTime")
    private Instant createdDateTime;

    @ApiModelProperty(example = "userCode")
    private String userCode;

    @ApiModelProperty(example = "validFrom")
    private Instant validFromDateTime;

    @ApiModelProperty(example = "validTo")
    private Instant validToDateTime;

    @ApiModelProperty(example = "description")
    private String description;

    @ApiModelProperty(example = "userMail")
    private String userEmail;

    @ApiModelProperty(example = "Heading")
    private MrktpHeadingDto mrktpHeading;

    @ApiModelProperty(example = "headingSub")
    private MrktpHeadingSubDto headingsub;

    @ApiModelProperty(example = "properties")
    private List<MrktpAdvertisePropertyDto> properties ;

    @ApiModelProperty(example = "messages")
    private List<MrktpAdvertiseQuoteDto> messages;
}
