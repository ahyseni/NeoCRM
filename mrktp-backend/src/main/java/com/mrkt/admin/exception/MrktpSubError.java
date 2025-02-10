package com.mrkt.admin.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class MrktpSubError implements Serializable {

    private final String object;

    private final String field;

    private final Serializable rejectedValue;

    private final String message;
}
