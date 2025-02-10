package com.mrkt.admin.model.account;


import com.mrkt.admin.model.employees.Employee;
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
@Table(name = "T_USERGROUP")
public class UserG extends MrktpAccountdataBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

    @OneToOne(mappedBy = "userG")
    private UserLogin userlogin = new UserLogin();

    @OneToOne(mappedBy = "userG")
    private Employee emploee = new Employee();

    @OneToMany(mappedBy = "userG")
    private List<UserType> userTypes = new ArrayList<>();

    @OneToMany(mappedBy = "userG")
    private List<UserRoles> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "userG")
    private List<UserGroups> userGroups = new ArrayList<>();

    @OneToMany(mappedBy = "userG")
    private List<Merchant> merchants = new ArrayList<>();

    @OneToMany(mappedBy = "userG")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userG")
    private List<Address> addresses = new ArrayList<>();


}
