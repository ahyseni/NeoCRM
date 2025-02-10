package com.mrkt.admin.dto.adds;

import com.mrkt.admin.dto.MrktpBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MrktpGroupBaseDto extends MrktpBaseDto {

	@ApiModelProperty(example = "")
	private String name;

	@ApiModelProperty(example = "")
	private String code;

	@ApiModelProperty(example = "")
	private List<MrktpSubgroupDto> subgroups;


}
