package com.mrkt.admin.model.masterdata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "T_GROUPS")
public class Group extends MrktpMasterdataBaseEntity {

	@Column(name = "group_name")
	private String name;

	@Column(name = "group_code")
	private String code;

	@Column(name = "description")
	private String description;


	@Column(name = "extrafield1")
	private String extrafield1;


	@Column(name = "extrafield2")
	private String extrafield2;

	@OneToMany(mappedBy = "group")
	private List<Section> sections = new ArrayList<>();

}
