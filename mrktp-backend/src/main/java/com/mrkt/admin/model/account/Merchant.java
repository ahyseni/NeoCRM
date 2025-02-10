package com.mrkt.admin.model.account;


import com.mrkt.admin.model.masterdata.Product;
import com.mrkt.admin.model.order.Order;
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
@Table(name = "T_MERCHANT")
public class Merchant extends MrktpAccountdataBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "kvk")
    private String kvk;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserG userG;

    @OneToMany(mappedBy = "merchant")
    private List<MerchantRating> merchantRatings = new ArrayList<>();

    @OneToMany(mappedBy = "merchant")
    private List<MerchantProperty> merchantProperties = new ArrayList<>();

    @OneToMany(mappedBy = "merchant")
    private List<Product> merchantProducts = new ArrayList<>();

    @OneToMany(mappedBy = "merchant")
    private List<Order> orderList = new ArrayList<>();


}
