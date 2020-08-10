package net.skhu.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Invoices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name="invoice_date",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	Date invoice_date;

	@Column(name="due_date",columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	Date due_date;

	double tax;
	double shipping;
	double amount_due;


	@ManyToOne
	@JoinColumn(name = "order_id")
	Orders orders;
}
