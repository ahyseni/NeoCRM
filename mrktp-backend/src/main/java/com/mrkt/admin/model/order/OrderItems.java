package com.mrkt.admin.model.order;

import com.mrkt.admin.model.account.Merchant;
import com.mrkt.admin.model.masterdata.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "masterdata.entity-chache")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_ORDER_ITEM")
public class OrderItems extends MrktpMasterdataBaseEntity {

    @Column(name = "ean_code")
    private String eancode;

    @Column(name = "ian_code")
    private String iancode;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "quantity")
    private int quantity;


    @Column(name = "net_value")
    private double net_value;

    @Column(name = "value_withvat")
    private double value_withvat;

    @Column(name = "rate_value")
    private double rate_value;

    @Column(name = "fix_rate")
    private double fix_rate;

    @Column(name = "discount")
    private double discount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
