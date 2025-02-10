package com.mrkt.admin.model.order;

import com.mrkt.admin.model.account.Merchant;
import com.mrkt.admin.model.account.UserG;
import com.mrkt.admin.model.masterdata.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

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
@Table(name = "T_ORDER")
public class Order extends MrktpMasterdataBaseEntity {


    @Column(name = "valid_from")
    private Instant validFromDateTime;

    @Column(name = "valid_to")
    private Instant validToDateTime;


    @Column(name = "comment")
    private String comment;


    @Column(name = "totalAmount")
    private double totalAmount;

    @CreatedDate
    @Column(name = "requiredOn", insertable = true, updatable = true)
    private String requiredOn;

    @CreatedDate
    @Column(name = "shippedOn", insertable = true, updatable = true)
    private String shippedOn;


    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "order")
    private List<OrderHistory> orderHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItemsList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserG userG;


}
