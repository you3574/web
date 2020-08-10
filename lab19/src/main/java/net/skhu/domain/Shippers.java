package net.skhu.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude= {"orders"})
@EqualsAndHashCode(exclude={"orders"})
@Entity
public class Shippers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@JsonIgnore
	@OneToMany(mappedBy = "shippers", fetch = FetchType.LAZY)
	List<Orders> orders;

	String company;
	String last_name;
	String first_name;
	String email_address;
	String job_title;
	String business_phone;
	String mobile_phone;
	String fax_number;
	String address;
	String city;
	String state_province;
	String zip_postal_code;
	String country_region;
	String web_page;
	String notes;
	String attachments;


}
