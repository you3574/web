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
@ToString(exclude= {"purchase_orders"})
@EqualsAndHashCode(exclude={"purchase_orders"})
@Entity
public class Purchase_order_status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@JsonIgnore
	@OneToMany(mappedBy = "puchase_orders_status", fetch = FetchType.LAZY)
	List<Purchase_orders> purchase_orders;

	String status;

}
