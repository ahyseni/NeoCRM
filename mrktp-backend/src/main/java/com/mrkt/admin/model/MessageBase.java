package com.mrkt.admin.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;

@Getter
@Setter
public class MessageBase extends MrktpBaseEntity {

    @Type(type = "text")
    @Column(length = 1000,columnDefinition = "TEXT")
    private String text;

    private String author;


}
