package com.mrkt.admin.model.masterdata;

import com.mrkt.admin.enums.OpportunityStage;
import com.mrkt.admin.enums.OpportunityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

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
@Table(name = "T_OPPORTUNITY")
public class Opportunity extends MrktpMasterdataBaseEntity {

    @Column(name = "opportunity_name")
    private String name;

    @Column(name = "opportunity_code")
    private String code;

    @Column(name = "description")
    private String description;


    @Column(name = "type")
    private OpportunityType opportunityType;

    @Column(name = "stage")
    private OpportunityStage opportunityStage;

    @Column(name = "amount")
    private double amount;

    @Column(name = "probability")
    private double probability;

    @Column(name = "expectedRevenue")
    private double expectedRevenue;

    @Column(name = "phone_number")
    private String phone;

    @CreatedDate
    @Column(name = "closeOn", insertable = true, updatable = true)
    private String closeOn;

//    @OneToMany(mappedBy = "account")
//    private List<Contact> contacts = new ArrayList<>();

}
