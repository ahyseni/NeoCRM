package com.mrkt.admin.dto.adds;

import com.mrkt.admin.dto.MrktpBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MrktpAdvertiseBaseQuoteDto extends MrktpBaseDto {

	@ApiModelProperty(example = "")
	private String advertiseOwner;

	@ApiModelProperty(example = "")
	private String guestUser;

	@ApiModelProperty(example = "")
	private Instant timeStamp;

	@ApiModelProperty(example = "")
	private String message;

	@ApiModelProperty(example = "")
	private MrktpAdvertiseDto advertise;

}
