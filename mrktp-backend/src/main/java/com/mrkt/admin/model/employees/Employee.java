package com.mrkt.admin.model.employees;

import com.mrkt.admin.enums.Gender;
import com.mrkt.admin.model.account.UserG;
import com.mrkt.admin.model.masterdata.MrktpMasterdataBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
@Cacheable
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "masterdata.entity-chache")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_EMPLOYEE")
public class Employee extends MrktpMasterdataBaseEntity {

    @Column(name = "firstName")
    private String firstName;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserG userG;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "employee_code")
    private String code;

    @Column(name = "city")
    private String city;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "phone_number")
    private String phone;

    @CreatedDate
    @Column(name = "birthdate", insertable = true, updatable = true)
    private String birthdate;

    @CreatedDate
    @Column(name = "hiringOn", insertable = true, updatable = true)
    private String hiringOn;


    @Column(name = "address")
    private String address;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

    @Lob
    @Column(name = "cv", columnDefinition = "CLOB")
    private byte[] cv;


    @Column(name = "extrafield1")
    private String extrafield1;


    @Column(name = "extrafield2")
    private String extrafield2;

    @OneToOne(mappedBy = "employee")
    private Department department = new Department();

    @OneToOne(mappedBy = "employee")
    private JobPosition jobPosition = new JobPosition();


}
