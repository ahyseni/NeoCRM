package com.mrkt.admin.model.account;


import com.mrkt.admin.model.masterdata.MrktpMasterdataBaseEntity;
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
@Table(name = "T_ROLE")
public class Role extends MrktpMasterdataBaseEntity {

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "role")
    private List<UserRoles> users = new ArrayList<>();


    @OneToMany(mappedBy = "userGroup")
    private List<UserGroups> userGroups = new ArrayList<>();


}
