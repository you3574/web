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
@ToString(exclude= {"employees_privileges"})
@EqualsAndHashCode(exclude={"employees_privileges"})
@Entity
public class Privileges {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@JsonIgnore
	@OneToMany(mappedBy = "privileges", fetch = FetchType.LAZY)
	List<Employee_privileges> employees_privileges;

	String privilege_name;

}
