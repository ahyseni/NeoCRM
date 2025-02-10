package com.mrkt.admin.model.masterdata;

import com.mrkt.admin.model.MrktpBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class MrktpMasterdataBaseEntity extends MrktpBaseEntity {

    @Column(name = "NAME_EN", nullable = false)
    private String nameEN;

    @Column(name = "NAME_NL", nullable = true)
    private String nameNL;

    @Column(name = "NAME_GR", nullable = true)
    private String nameGR;

    @Column(name = "NAME_DE", nullable = true)
    private String nameDE;

    @Column(name = "NAME_AL", nullable = true)
    private String nameAL;

    @Column(name = "NAME_FR", nullable = true)
    private String nameFR;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;
}
