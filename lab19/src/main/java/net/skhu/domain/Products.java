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
@ToString(exclude= {"purchase_order_details","inventory_transactions","order_details"})
@EqualsAndHashCode(exclude={"purchase_order_details","inventory_transactions","order_details"})
@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String product_name;
	String category;

	@JsonIgnore
	@OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
	List<Purchase_order_details> purchase_order_details;

	@JsonIgnore
	@OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
	List<inventory_transactions> inventory_transactions;

	@JsonIgnore
	@OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
	List<Order_details> order_details;

	double standard_cost;
	int reorder_level;
	int target_level;

	String product_code;
	String description;
	double list_price;
	String quantity_per_unit;
	int discontinued;
	int minimem_reorder_quantity;



}
