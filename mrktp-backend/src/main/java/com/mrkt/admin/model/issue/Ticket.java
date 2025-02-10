package com.mrkt.admin.model.issue;

import com.mrkt.admin.enums.*;
import com.mrkt.admin.model.masterdata.Contact;
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
@Table(name = "T_TICKET")
public class Ticket extends MrktpMasterdataBaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "description")
	private String description;


	@Column(name = "type")
	private TicketType ticketType;

	@Column(name = "status")
	private TicketStatus ticketStatus;


	@Column(name = "priority")
	private TicketPriority ticketPriority;


	@Column(name = "severity")
	private TicketSeverity ticketSeverity;


	@Column(name = "source")
	private TicketSource ticketSource;

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

    @CreatedDate
    @Column(name = "submittedOn", insertable = true, updatable = true)
    private String submittedOn;

    @CreatedDate
    @Column(name = "incidentDate", insertable = true, updatable = true)
    private String incidentDate;


//	@OneToMany(mappedBy = "account")
//	private List<Contact> contacts = new ArrayList<>();

}
