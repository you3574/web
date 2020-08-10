package net.skhu.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(exclude= {"orderDetails"})
@EqualsAndHashCode(exclude={"orderDetails"})
@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@Column(name="order_date")
	Date orderDate;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	Customer customer;

	@JsonIgnore
	@OneToMany(mappedBy="order")
	List<OrderDetails> orderDetails;
}