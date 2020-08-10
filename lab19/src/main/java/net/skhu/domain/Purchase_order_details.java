package net.skhu.domain;

import java.util.Date;

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
@ToString(exclude= {"products","purchase_orders"})
@EqualsAndHashCode(exclude={"products","purchase_orders"})
@Entity
public class Purchase_order_details {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	double quantity;
	double unit_cost;
	Date date_received;
	int posted_to_inventory;
	int inventory_id;


	@ManyToOne
	@JoinColumn(name = "product_id")
	Products products;


	@ManyToOne
	@JoinColumn(name = "purchase_order_id")
	Purchase_orders purchase_orders;

}
