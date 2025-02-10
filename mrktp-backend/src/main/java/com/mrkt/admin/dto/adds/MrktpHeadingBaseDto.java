package com.mrkt.admin.dto.adds;

import com.mrkt.admin.dto.MrktpBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MrktpHeadingBaseDto extends MrktpBaseDto{
	@ApiModelProperty(example = "")
	private String heading_code;

	@ApiModelProperty(example = "")
	private String heading;

	@ApiModelProperty(example = "")
	private List<MrktpAdvertiseDto> advertises = new ArrayList<>();

	@ApiModelProperty(example = "")
	private List<MrktpHeadingSubDto> heading_sub = new ArrayList<>();
}
