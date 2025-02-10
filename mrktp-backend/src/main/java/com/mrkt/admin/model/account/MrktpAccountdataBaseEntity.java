package com.mrkt.admin.model.account;

import com.mrkt.admin.model.MrktpBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class MrktpAccountdataBaseEntity extends MrktpBaseEntity {

    @Column(name="NAME_EN", nullable = false)
    private String nameEN;

    @Column(name="ACTIVE", nullable = false)
    private Boolean active;
}
