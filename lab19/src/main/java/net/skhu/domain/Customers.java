package net.skhu.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude= {"orders"})
@EqualsAndHashCode(exclude={"orders"})
@Entity
public class Customers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String company;

	@Column(name = "last_name")
	String lastName;

	@Column(name = "first_name")
	String firstName;

	@Column(name = "job_title")
	String jobTitle;

	@Column(name = "business_phone")
	String businessPhone;

	String city;

	String business_phone;
	String home_phone;
	String mobile_phone;
	String fax_number;

	@Lob //longtext
	String address;


	String state_province;
	String zip_postal_code;
	String country_region;

	@Lob //longtext
	String web_page;
	@Lob //longtext
	String notes;
	@Lob //longtext
	String attachments;


	@JsonIgnore
	@OneToMany(mappedBy = "customers", fetch = FetchType.LAZY)
	List<Orders> orders;


}
