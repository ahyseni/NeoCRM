package com.mrkt.admin.model.account;

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
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region = "account.entity-chache")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_USERLOGIN")
public class UserLogin extends MrktpAccountdataBaseEntity {

	@Column(name = "email")
	private String email;

	@Column(name = "pwd")
	private String pwd;


	@OneToOne
	@JoinColumn(name = "user_id")
	private UserG userG;

}
