package com.mrkt.admin.dto.account;

import com.mrkt.admin.dto.MrktpBaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountBaseDto extends MrktpBaseDto {

    @ApiModelProperty(example = "nameEN")
    private String nameEN;

    @ApiModelProperty(example = "active")
    private Boolean active;

    public AccountBaseDto(String id, String nameEN, Boolean active) {
        super(id);
        this.nameEN = nameEN;
        this.active = active;
    }
}
