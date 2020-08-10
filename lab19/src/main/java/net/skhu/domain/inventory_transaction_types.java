package net.skhu.domain;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class inventory_transaction_types {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String type_name;
	@JsonIgnore
	@OneToMany(mappedBy = "inventory_transaction_types", fetch = FetchType.LAZY)
	List<inventory_transactions> inventory_transactions;
}
