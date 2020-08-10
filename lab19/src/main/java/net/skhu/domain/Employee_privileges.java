package net.skhu.domain;

import java.util.List;

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

@Data
@Entity
public class Employee_privileges {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	Employees employees;

	@ManyToOne
	@JoinColumn(name = "privilege_id")
	Privileges privileges;

	@JsonIgnore
	@OneToMany(mappedBy = "Employee_privileges", fetch = FetchType.LAZY)
	List<Orders_tax_status> orders_tax_status;

	@JsonIgnore
	@OneToMany(mappedBy = "Employee_privileges", fetch = FetchType.LAZY)
	List<Orders_status> orders_status;

	@JsonIgnore
	@OneToMany(mappedBy = "Employee_privileges", fetch = FetchType.LAZY)
	List<Privileges> privileges1;

}
