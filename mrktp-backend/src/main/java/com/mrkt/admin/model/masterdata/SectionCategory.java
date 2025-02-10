package com.mrkt.admin.model.masterdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "masterdata.entity-chache")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_SECTION_CATEGORIES")
public class SectionCategory extends MrktpMasterdataBaseEntity {

    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "extrafield1")
    private String extrafield1;


    @Column(name = "extrafield2")
    private String extrafield2;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToMany(mappedBy = "categories")
    private List<SectionProperty> mrktpSectionProperties = new ArrayList<>();

}
