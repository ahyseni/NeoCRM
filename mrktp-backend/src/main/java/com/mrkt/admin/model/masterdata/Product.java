package com.mrkt.admin.model.masterdata;

import com.mrkt.admin.model.account.Merchant;
import com.mrkt.admin.model.order.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Table(name = "T_PRODUCT")
public class Product extends MrktpMasterdataBaseEntity {

    @Column(name = "ean_code")
    private String eancode;

    @Column(name = "ian_code")
    private String iancode;

    @Column(name = "name")
    private String name;

    @Column(name = "valid_from")
    private Instant validFromDateTime;

    @Column(name = "valid_to")
    private Instant validToDateTime;


    @Column(name = "description")
    private String description;


    @Column(name = "stock")
    private int stock;


    @Column(name = "price")
    private double price;


    @Column(name = "extrafield1")
    private String extrafield1;


    @Column(name = "extrafield2")
    private String extrafield2;


    @Column(name = "email")
    private String userEmail;

    @Column(name = "brand")
    private String brand;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionproperties_id")
    private SectionProperty sectionproperties;

    @OneToMany(mappedBy = "product")
    private List<ProductProperty> properties = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderItems> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductQuote> messages = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductSpecification> specifications = new ArrayList<>();
}
