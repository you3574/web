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
@ToString(exclude= {"order_details"})
@EqualsAndHashCode(exclude={"order_details"})
@Entity
public class Order_details_status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@JsonIgnore
	@OneToMany(mappedBy = "order_details_status", fetch = FetchType.LAZY)
	List<Order_details> order_details;

	String status_name;


}
