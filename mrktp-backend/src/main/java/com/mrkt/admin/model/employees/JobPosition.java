package com.mrkt.admin.model.employees;

import com.mrkt.admin.model.masterdata.MrktpMasterdataBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "T_JOBPOSITION")
public class JobPosition extends MrktpMasterdataBaseEntity {

    @Column(name = "name")
    private String name;


    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
