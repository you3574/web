package net.skhu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Sales_reports {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int group_by;
	String display;
	String title;
	String filte_row_sourse;

	@Column(name="default")
	int d;
}
