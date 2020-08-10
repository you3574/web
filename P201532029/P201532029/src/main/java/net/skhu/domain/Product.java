package net.skhu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude= {"order_details"})
@EqualsAndHashCode(exclude={"order_details"})
@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "product_code")
	String productCode;

	@Column(name = "product_name")
	String productName;

	@Column(name = "standard_cost")
	Double standardCost;

	@Column(name = "list_price")
	Double listPrice;

	@Column(name = "quantity_per_unit")
	String quantity;

	@Column(name = "category")
	String category;

/*	@JsonIgnore
	@OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
	List<Order_details> order_details;*/

}