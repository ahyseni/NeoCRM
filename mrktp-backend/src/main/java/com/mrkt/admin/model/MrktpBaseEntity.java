package com.mrkt.admin.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public abstract class MrktpBaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID")
    String id;

    @Version
    @Column(name = "VERSION")
    private Long version;

    @CreatedDate
    @Column(name = "CREATION_DATE", insertable = true, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "MODIFICATION_DATE", insertable = false, updatable = true)
    private Date modificationdDate;


    @PrePersist
    public void setCreatedDate() {
        this.createdDate = this.modificationdDate = new Date();
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.modificationdDate = new Date();
    }

}
