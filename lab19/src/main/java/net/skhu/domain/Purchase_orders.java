package net.skhu.domain;

import java.sql.Date;
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
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude= {"inventory_transactions","purchase_order_details","purchase_order_status","employees","suppliers"})
@EqualsAndHashCode(exclude={"inventory_transactions","purchase_order_details","purchase_order_status","employees","suppliers"})
@Entity
public class Purchase_orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@JsonIgnore
	@OneToMany(mappedBy = "Purchase_orders", fetch = FetchType.LAZY)
	List<inventory_transactions> inventory_transactions;

	@JsonIgnore
	@OneToMany(mappedBy = "Purchase_orders", fetch = FetchType.LAZY)
	List<Purchase_order_details> purchase_order_details;

	@ManyToOne
	@JoinColumn(name = "status_id")
	Purchase_order_status purchase_order_status;

	@ManyToOne
	@JoinColumn(name = "created_by")
	Employees employees;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	Suppliers suppliers;

	Date submitted_date;
	Date creation_date;
	Date expected_date;
	double shipping_fee;
	double taxes;
	Date payment_date;
	double patment_amount;
	String patment_method;
	String notes;
	int approved_by;
	Date approved_Date;
	int submitted_by;


}
