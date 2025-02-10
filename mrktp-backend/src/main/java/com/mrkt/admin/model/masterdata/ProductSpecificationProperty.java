package com.mrkt.admin.model.masterdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Cacheable
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "masterdata.entity-chache")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_PRODUCT_SPECIFICATION_PROPERTY")
public class ProductSpecificationProperty extends MrktpMasterdataBaseEntity {

    @Column(name = "name")
    private String name;


    @Column(name = "value")
    private String value;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productSpecification_id")
    private ProductSpecification productSpecification;


}
