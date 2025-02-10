package com.mrkt.admin.model.masterdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "masterdata.entity-cache")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TENANTS")
public class Tenant extends MrktpMasterdataBaseEntity {

    @Column(name = "tenant_name", nullable = false)
    private String tenantName;

    @Column(name = "license_type", nullable = false)
    private String licenseType;

    @Column(name = "license_limits", nullable = false)
    private Integer licenseLimits;

    @Column(name = "subscription_start", nullable = false)
    private LocalDate subscriptionStart;

    @Column(name = "subscription_end", nullable = false)
    private LocalDate subscriptionEnd;
}
