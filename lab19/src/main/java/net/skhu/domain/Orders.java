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
@ToString(exclude= {"invoices","inventory_transactions","order_details","employees","shippers","order_tax_status","orders_status","customers"})
@EqualsAndHashCode(exclude={"invoices","inventory_transactions","order_details","employees","shippers","order_tax_status","orders_status","customers"})
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@JsonIgnore
	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
	List<Invoices> invoices;

	@JsonIgnore
	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
	List<inventory_transactions> inventory_transactions;

	@JsonIgnore
	@OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
	List<Order_details> order_details;

	@ManyToOne
    @JoinColumn(name = "employee_id")
    Employees employees;

	@ManyToOne
    @JoinColumn(name = "shipper_id")
    Shippers shippers;

	@ManyToOne
    @JoinColumn(name = "tax_status_id")//
    Orders_tax_status order_tax_status;

	@ManyToOne
    @JoinColumn(name = "status_id")
    Orders_status orders_status;

	@ManyToOne
    @JoinColumn(name = "customer_id")
    Customers customers;

	Date order_date;
	Date shipped_date;
	String ship_name;
	String ship_address;
	String ship_city;
	String shi_state_province;
	String ship_zip_postal_code;
	String ship_country_region;
	double shipping_fee;
	double taxes;
	String payment_type;
	Date paid_date;
	String notes;
	double tax_rate;
}
