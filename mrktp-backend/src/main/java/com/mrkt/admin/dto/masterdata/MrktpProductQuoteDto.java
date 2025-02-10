package com.mrkt.admin.dto.masterdata;

import com.mrkt.admin.dto.MrktpBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class MrktpProductQuoteDto extends MasterdataBaseDto {

    @ApiModelProperty(example = "advertiseOwner")
    private String advertiseOwner;

    @ApiModelProperty(example = "guestUser")
    private String guestUser;

    @ApiModelProperty(example = "timeStamp")
    private Instant timeStamp;

    @ApiModelProperty(example = "message")
    private String message;


    @ApiModelProperty(example = "mrktpProductID")
    private String productId;

}
