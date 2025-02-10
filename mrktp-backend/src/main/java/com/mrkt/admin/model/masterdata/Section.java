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
@Table(name = "T_SECTIONS")
public class Section extends MrktpMasterdataBaseEntity {

	@Column(name = "section_code")
	private String code;
	@Column(name = "section_name")
	private String name;

	@Column(name = "section_description")
	private String description;


	@Column(name = "section_extrafield1")
	private String extrafield1;


	@Column(name = "section_extrafield2")
	private String extrafield2;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group group;

	@OneToMany(mappedBy = "section")
	private List<SectionCategory> sectionCategories = new ArrayList<>();


}
