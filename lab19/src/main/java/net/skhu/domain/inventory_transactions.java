package net.skhu.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class inventory_transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int transaction_type;

	@Column(name="transaction_create_date",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	Date transaction_create_date;

	int product_id;
	int quantity;
	int purchase_order_id;
	int customer_order_id;
	String comments;


	@ManyToOne
	@JoinColumn(name = "customer_order_id")
	Orders orders;

	@ManyToOne
	@JoinColumn(name = "product_id")
	Products products;

	@ManyToOne
	@JoinColumn(name = "purchase_order_id")
	Purchase_orders purchase_orders;

	@ManyToOne
	@JoinColumn(name = "transaction_type")
	inventory_transaction_types inventory_transaction_types;

	@JsonIgnore
	@OneToMany(mappedBy = "inventory_transactions", fetch = FetchType.LAZY)
	List<Purchase_order_details> purchase_order_details;

}
