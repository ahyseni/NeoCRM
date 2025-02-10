package com.mrkt.admin.model.account;


import com.mrkt.admin.model.masterdata.Product;
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
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "account.entity-chache")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_MERCHANT_PROPERTY")
public class MerchantProperty extends MrktpAccountdataBaseEntity {

    @Column(name = "creditStatus")
    private String creditStatus;

    @Column(name = "appliedRate")
    private String appliedRate;

    @Column(name = "appliedFix")
    private String appliedFix;

    @Column(name = "appliedRateActive")
    private boolean appliedRateActive;

    @Column(name = "appliedFixActive")
    private String appliedFixActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

}
