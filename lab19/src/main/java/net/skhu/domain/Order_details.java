package net.skhu.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(exclude= {"orders","order_details_status","products"})
@EqualsAndHashCode(exclude={"orders","order_details_status","products"})
@Entity
public class Order_details {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	Orders orders;

	@ManyToOne
	@JoinColumn(name = "status_id")
	Order_details_status order_details_status;

	@ManyToOne
	@JoinColumn(name = "product_id")
	Products products;

	double quantity;
	double unit_price;
	double discount;
	int status_id;
	Date date_allocated;
	int purchase_order_id;
	int inventory_id;
}
