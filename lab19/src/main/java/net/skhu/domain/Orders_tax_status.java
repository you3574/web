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
@ToString(exclude= {"orders"})
@EqualsAndHashCode(exclude={"orders"})
@Entity
public class Orders_tax_status {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;


	@JsonIgnore
	@OneToMany(mappedBy = "tax_status_id", fetch = FetchType.LAZY)
	List<Orders> orders;

}
