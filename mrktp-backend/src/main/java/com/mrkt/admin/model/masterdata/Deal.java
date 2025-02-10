package com.mrkt.admin.model.masterdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "masterdata.entity-cache")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DEALS")
public class Deal extends MrktpMasterdataBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = true)
    private Contact contact;

    @Column(name = "deal_name", nullable = false)
    private String dealName;

    @Column(name = "description")
    private String description;

    @Column(name = "stage", nullable = false)
    private String stage;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "close_date")
    private LocalDate closeDate;
}
