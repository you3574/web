package net.skhu.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude= {"orders"})
@EqualsAndHashCode(exclude={"orders"})
@Entity
@Table(name="customers")
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@Column(name="last_name")
	String lastName;

	@Column(name="first_name")
	String firstName;

	@Column(name="email_address")
	String emailAddress;

	@Column(name="job_title")
	String jobTitle;

	@Column(name="business_phone")
	String businessPhone;

	String city;

	@JsonIgnore
	@OneToMany(mappedBy="customer")
	List<Order> orders;
}