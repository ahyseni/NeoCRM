package com.mrkt.admin.dto.adds;

import com.mrkt.admin.dto.MrktpBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MrktpAdvertisePropertyBaseDto extends MrktpBaseDto {

	@ApiModelProperty(example = "")
	private String name;

	@ApiModelProperty(example = "")
	private byte[] picture;

}
