package net.skhu.model;

import lombok.Data;

@Data
public class ProductModel {

	String productCode;

	String productName;

	Double standardCost;
	Double listPrice;

	String quantity;

	String category;
}