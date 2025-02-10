package com.mrkt.admin.model.account;


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
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "account.entity-chache")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_ADDRESS")
public class Address extends MrktpAccountdataBaseEntity {

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "street")
    private String street;


    @Column(name = "postcode")
    private String postcode;

    @Column(name = "houseNumber")
    private String houseNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserG userG;

}
