package com.mrkt.admin.model.masterdata;

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
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region = "masterdata.entity-chache")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_ACCOUNT")
public class Account extends MrktpMasterdataBaseEntity {

	@Column(name = "account_name")
	private String name;

	@Column(name = "account_code")
	private String code;

	@Column(name = "description")
	private String description;


	@Column(name = "country_name")
	private String country_name;

	@Column(name = "account_Type")
	private AccessType account_Type;

	@Column(name = "vat_number")
	private String vat;

	@Column(name = "city")
	private String city;

	@Column(name = "postcode")
	private String postcode;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "email")
    private String email;


    @Column(name = "address")
    private String address;

//	@OneToMany(mappedBy = "account")
//	private List<Contact> contacts = new ArrayList<>();

}
