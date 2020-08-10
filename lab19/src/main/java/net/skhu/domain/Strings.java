package net.skhu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Strings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	String string_data;
}
