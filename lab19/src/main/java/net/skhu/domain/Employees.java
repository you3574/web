package net.skhu.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(exclude= {"purchase_orders","employee_privileges"})
@EqualsAndHashCode(exclude={"purchase_orders","employee_privileges"})
@Entity
public class Employees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "email_address")
	String emailAddress;

	@Column(name = "job_title")
	String jobTitle;

	@Column(name = "business_phone")
	String businessPhone;

	String city;

	String name;

	@JsonIgnore
	@OneToMany(mappedBy = "employees", fetch = FetchType.LAZY)
	List<Purchase_orders> purchase_orders;

	@JsonIgnore
	@OneToMany(mappedBy = "employees", fetch = FetchType.LAZY)
	List<Employee_privileges> employee_privileges;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	Employees employees;
}
