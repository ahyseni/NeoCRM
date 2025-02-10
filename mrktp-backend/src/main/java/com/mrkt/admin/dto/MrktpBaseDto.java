package com.mrkt.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
public abstract class MrktpBaseDto implements Serializable {

    @ApiModelProperty(example = "550e8400-e29b-41d4-a716-446655440000", notes = "Unique identifier")
    private String id;

    @ApiModelProperty(example = "0")
    private Long version;

//    @ApiModelProperty(example = "550e8400-e29b-41d4-a716-446655440000", notes = "Identifier of the creator")
//    private String createdBy;
//
//    @ApiModelProperty(example = "550e8400-e29b-41d4-a716-446655440000", notes = "Identifier of the assigned")
//    private String assignedTo;

    @ApiModelProperty(example = "")
    private Instant modificationDate;


    public MrktpBaseDto(String id) {
        this.id = id;
    }
}
